cmake_minimum_required(VERSION 3.21)
project(test)
set(CMAKE_CXX_STANDARD 14)

#引入头文件
include_directories(/Users/judy/doc/C_lang/uthash/include)
#引入库文件
link_directories(/Users/judy/doc/C_lang/uthash/src)

#第1个项目
set(myPath 01_1711)
set(myProName 01_Pro)
project(${myProName})
file(GLOB files
        base/${myPath}/*.cpp
        base/${myPath}/*.c
        base/${myPath}/*.h)
add_executable(${myProName} ${files})

#第2个项目
set(myPath 02_1717)
set(myProName 02_Pro)
project(${myProName})
file(GLOB files
        base/${myPath}/*.cpp
        base/${myPath}/*.c
        base/${myPath}/*.h)
add_executable(${myProName} ${files})

#第3个项目
set(myPath 03_1696)
set(myProName ${myPath})
project(${myProName})
file(GLOB files
        base/${myPath}/*.cpp
        base/${myPath}/*.c
        base/${myPath}/*.h)
add_executable(${myProName} ${files})

#第4个项目
set(myPath 04_1695)
set(myProName ${myPath})
project(${myProName})
file(GLOB files
        base/${myPath}/*.cpp
        base/${myPath}/*.c
        base/${myPath}/*.h)
add_executable(${myProName} ${files})

#第6个项目
set(myPath 06_1680)
set(myProName ${myPath})
project(${myProName})
file(GLOB files
        base/${myPath}/*.cpp
        base/${myPath}/*.c
        base/${myPath}/*.h)
add_executable(${myProName} ${files})
