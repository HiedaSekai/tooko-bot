#!/usr/bin/env bash

BUILD_DIR="target/native"

rm -rf $BUILD_DIR && mkdir -p $BUILD_DIR && cp src/main/jni/* $BUILD_DIR

cd $BUILD_DIR

git clone https://github.com/tdlib/td.git td

cd td && mkdir build && cd build

export CXXFLAGS="-stdlib=libc++"

CC=/usr/bin/clang CXX=/usr/bin/clang++ cmake \
  -DCMAKE_BUILD_TYPE=Release \
  -DCMAKE_INSTALL_PREFIX:PATH=. \
  -DTD_ENABLE_JNI=ON . || exit

cmake --build . --target install || exit

cd ../..

mkdir build && cd build

CC=/usr/bin/clang CXX=/usr/bin/clang++ cmake \
  -DCMAKE_BUILD_TYPE=Release \
  -DCMAKE_INSTALL_PREFIX:PATH=td/tdlib \
  -DTd_DIR:PATH=$(readlink -e td/lib/cmake/Td) .

cmake --build . --target install
