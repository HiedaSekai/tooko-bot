#!/bin/bash

info() { echo "I: $*"; }

error() {

  echo "E: $*"
  exit 1

}

if [ -x "$(command -v wget)" ]; then

  download() { wget "$2" -O "$1"; }

elif [ -x "$(command -v curl)" ]; then

  download() { curl "$2" -o "$1"; }

else

  error "curl or wget required."

fi

function installTDLib() {

  case "$(uname -m)" in

  aarch64 | arm64) arch="aarch64" ;;
  x86_64 | amd64) arch="x86_64" ;;
  i[3-6]86 | x86) arch="x86" ;;
  armv[7-8]) arch="armv7" ;;
  arm*) arch="armv5" ;;

  *)

    echo "<< 无当前架构的预编译TDLib链接库"
    return 1
    ;;

  esac

  download "libs/libtdjni.so" "https://gitlab.com/tooko/td/raw/$arch/libtdjni.so"

  return $?

}

function installWebP() {

  case "$(uname -m)" in

  x86_64 | amd64) arch="amd64" ;;
  i[3-6]86 | x86) arch="i386" ;;
  aarch64 | arm64) arch="arm64" ;;
  arm*) arch="arm" ;;

  *)
    echo "<< 无当前架构的预编译Webp链接库"
    return 1
    ;;

  esac

  download "libs/libwebp-imageio.so" "https://gitlab.com/tooko/tooko-webp/raw/binary/$arch/libwebp-imageio.so"

  return $?

}

if [ ! "$1" ]; then

  echo "./tooko [ init | update | run | log | start | stop | ... ]"

  exit

fi

if [ "$1" == "init" ]; then

  echo ">> 写入服务"

  cat >/etc/systemd/system/tooko.service <<EOF
[Unit]
Description=Tooko
After=network.target
Wants=network.target

[Service]
Type=simple
WorkingDirectory=$(readlink -e ./)
ExecStart=/bin/bash tooko.sh run
Restart=on-failure
RestartPreventExitStatus=100

[Install]
WantedBy=multi-user.target
EOF

  cat >/etc/systemd/system/nsfw-server.service <<EOF
[Unit]
Description=Tooko
After=network.target
Wants=network.target

[Service]
Type=simple
WorkingDirectory=$(readlink -e ./)/nsfw
ExecStart=/bin/bash nsfw_server.sh
Restart=on-failure
RestartPreventExitStatus=1

[Install]
WantedBy=multi-user.target
EOF

  systemctl daemon-reload

  echo ">> 写入启动项"

  systemctl enable tooko &>/dev/null
  systemctl enable nsfw-server &>/dev/null

  echo "<< 完毕."

  exit

elif [ "$1" == "run" ]; then

  [ -d libs ] || mkdir -p libs

  if [ ! -f "libs/libtdjni.so" ]; then

    echo ">> 下载 TDLib 可执行文件"

    installTDLib || exit 100

  fi

  if [ ! -f "libs/libwebp-imageio.so" ]; then

    echo ">> 下载 WebP 支持库"

    installWebP || exit 100

  fi

  [ -d "target/classes" ] || mvn compile

  mvn exec:java -Dexec.mainClass="tookox.Launcher"

elif [ "$1" == "start" ]; then

  systemctl start tooko

  ./tooko.sh log

elif [ "$1" == "restart" ]; then

  systemctl restart tooko

  ./tooko.sh log

elif [ "$1" == "update" ]; then

  git fetch &>/dev/null

  if [ "$(git rev-parse HEAD)" = "$(git rev-parse FETCH_HEAD)" ]; then

    echo "<< 没有更新"

    exit 1

  fi

  echo ">> 检出更新 $(git rev-parse FETCH_HEAD)"

  git reset --hard FETCH_HEAD &> /dev/null

  mvn clean compile

  exit $?

elif [ "$1" == "log" ]; then

  journalctl -u tooko -f

elif [ "$1" == "logs" ]; then

  shift 1

  journalctl -u tooko --no-tail $@

else

  systemctl "$1" tooko

fi
