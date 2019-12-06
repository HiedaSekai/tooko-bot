#!/bin/bash

function installTDLib() {

  case "$(uname -m)" in
  
      aarch64|arm64) arch="arm64" ;;
      arm*)
        
        HARDFLOAT=$(readelf -a /usr/bin/readelf | grep armhf)
        
        if [ -z "$HARDFLOAT" ]; then
        
          arch="armhf"
          
        else
        
          arch="armel"
        
        fi
        
      ;;
      x86_64|amd64) arch="amd64" ;;
      i[3-6]86|x86) arch="i386";;
  
      *) echo "<< 无当前架构的预编译TDLib链接库"; return 1 ;;
  
  esac
  
  wget -O "libs/jni/libtdjni.so" "https://gitlab.com/tooko/tooko-tdlib/raw/binary/$arch/libtdjni.so"
  
  return $?
  
}

function installWebP() {

  case "$(uname -m)" in
  
      aarch64|arm64) arch="amd64" ;;
      arm*) arch="arm" ;;
      x86_64|amd64) arch="amd64" ;;
      i[3-6]86|x86) arch="i386";;
  
      *) echo "<< 无当前架构的预编译Webp链接库"; return 1 ;;
  
  esac
  
  wget -O "libs/jni/libwebp-imageio.so" "https://gitlab.com/tooko/tooko-webp/raw/binary/$arch/libwebp-imageio.so"
  
  return $?
  
}

if [ ! $1 ]; then
  
  echo "./tooko [ init | update | run | log | start | stop | ... ]"
  
  exit

fi

if [ $1 == "init" ]; then
  
  if [ ! $2 ]; then
  
    echo "./tooko init [BotToken]"
    
    exit
  
  fi
  
  echo ">> 写入配置"
  
  cp .config.json config.json
  
  sed -i "s/BotToken/$2/g" config.json
 
  echo ">> 写入服务"
  
  cat > /etc/systemd/system/tooko.service << EOF
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

  systemctl daemon-reload
  
  echo ">> 写入启动项"

  systemctl enable tooko.service &> /dev/null
  
  echo "<< 完毕."
  
  exit

elif [ $1 == "run" ]; then

  [ -d "libs/jni" ] || mkdir -p "libs/jni"
  
  if [ ! -f "libs/jni/libtdjni.so" ]; then
  
    echo ">> 下载 TDLib 可执行文件"
  
    installTDLib || exit 100
  
  fi
  
  if [ ! -f "libs/jni/libwebp-imageio.so" ]; then
  
    echo ">> 下载 WebP 支持库"
  
    installWebP || exit 100
  
  fi
  
  [ -d "target/classes" ] || mvn compile
  
  mvn exec:java -Dexec.mainClass="tooko.Launcher"

elif [ $1 == "start" ]; then
  
  systemctl start tooko
  
  ./tooko.sh log

elif [ $1 == "restart" ]; then
  
  systemctl restart tooko
  
  ./tooko.sh log
  
elif [ $1 == "update" ]; then

  git fetch &> /dev/null

  if [ $(git rev-parse HEAD) = $(git rev-parse FETCH_HEAD) ]; then
  
    echo "<< 没有更新"
  
    exit 1

  fi
  
  echo ">> 检出更新 $(git rev-parse FETCH_HEAD)"

  git checkout -f FETCH_HEAD &> /dev/null

  git branch -D master  &> /dev/null

  git checkout master &> /dev/null

  mvn clean compile

  exit $?

elif [ $1 == "log" ]; then

  journalctl -u tooko.service -f

else

  systemctl $1 tooko

fi