cmake_minimum_required(VERSION 3.21)



set(CMAKE_CXX_STANDARD 14)
#第1个项目
set(myPath 01_test)
set(myProName 01_test)
project(${myProName})
file(GLOB ${myProName}
        base/${myPath}/*.cpp
        base/${myPath}/*.c
        base/${myPath}/*.h)
add_executable(${myProName} ${${myProName}})
