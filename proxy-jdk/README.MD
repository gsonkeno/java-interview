## jdk动态代理
- 通过设置系统属性 sun.misc.ProxyGenerator.saveGeneratedFiles=true，可以在/com/sun/proxy目录下生成代理类字节码文件
- 最终通过字节码生成的代理类实现了业务接口，且继承了Proxy类

### 参考文章
- [Java动态代理原理和源码分析](https://www.jianshu.com/p/0a7dfb701677)

