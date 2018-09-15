# TestAboutService
+ service用intent显式启动
+ 在这里做点笔记
+ startService  和  bindService两种方式可以启动
+ 生命周期，两种启动方式周期不同
+ 重复调用startService不会重新调用onCreate()但是会重新调用onStart()
+ 重复调用bindService并不会变化
+ 注意线程和service的区别
