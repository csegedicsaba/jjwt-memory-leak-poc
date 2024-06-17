# jjwt-memory-leak-poc

POC for native memory leak using jjwt token with GZIP compression.

**Using multiple threads to reproduce memory leak is important! With only one thread there is no memory leak.**

**Build:**

`mvn clean install`

`docker build -t poc .`


**Run:**

`docker run -it --name poc poc`

**Check:**

check memory usage using `docker stats poc` -> cca. 5 GB

check memory usage of java process from container: `jcmd 1 VM.native_memory`  -> Total: reserved=2020375KB, **committed=471707KB**


Using https://github.com/jwtk/jjwt/issues/949 fix, there is no memory leak.


Tested on windows using docker v4.31.1