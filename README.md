判断GPS坐标是否在国内

根据ZCChinaLocation 来实现的相关代码，其实就是这个oc代码的java版本，最近用到了这么个东西所以就给实现了一下。由于我是一个非专业Java程序员，因而代码写的比较糟糕，有什么不周到的地方还希望大家积极补充。
实现原理：

    基本思路是：把整个行政区域划分为几个小的矩形，然后再排除掉一些矩形区域。只要一个点在限定的区域内，并且不在排除的区域内，则判定成功，否则失败。下图中蓝色区域为限定区域，红色区域为排除区域。这种快速判定的方法是从Nokia map中挖掘出来的。

    优点：快速，相对准确；

    缺点：边界误差较大 

原理图


![image](https://github.com/zcsoft/ZCChinaLocation/raw/master/Image/area.png)


iOS上除了这个办法，还有个更为精确的办法，但是相对来说速度要差一些，可以参考这个链接CLGeocoder

基本原理：

    CLGeocoder geoCoder = [[CLGeocoder alloc] init]; [geoCoder reverseGeocodeLocation:location completionHandler:^(NSArray<CLPlacemark > * _Nullable placemarks, NSError * _Nullable error) { }];

    调用这个方法我是用ISOcountryCode这个属性来判断的，这个值港澳台都有各自对应的值，具体的code可以去这个网站上去查 Country Codes List 此外还有一个问题就是这个方法有时候会有一定的延迟，还有就是苹果也建议不要连续调用这个方法。

    其实个人感觉这个简单粗暴的算法也基本够用了，因为这些周边的地区大多数都是人烟稀少，即使部分数据存在误差也无伤大雅。比较郁闷的是Android上并没有提供类似iOS上的方法。所以目前我的代码中也是使用了这个简单粗暴的代码来实现的。当然，如果要做的更加详细可以继续细分这些矩形区域。欢迎大家补充和完善这个代码。 

Findu （我的一个好友位置共享应用）

欢迎大家试用我的好友位置共享应用： 官网： 中文：http://www.findu.co 英文：http://www.findu.today

