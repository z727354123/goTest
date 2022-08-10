cmake_minimum_required(VERSION 3.21)


#引入头文件
include_directories(/Users/judy/doc/C_lang/uthash/include)
#引入库文件
link_directories(/Users/judy/doc/C_lang/uthash/src)

set(CMAKE_CXX_STANDARD 14)
#第1个项目
set(myPath 01_1711)
set(myProName ${myPath})
project(${myProName})
file(GLOB ${myProName}
        base/${myPath}/*.cpp
        base/${myPath}/*.c
        base/${myPath}/*.h)
add_executable(${myProName} ${${myProName}})
