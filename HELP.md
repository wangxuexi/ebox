# 部署帮助

### docker登录地址
docker login --username=fijo@fijo.com.cn registry.cn-hangzhou.aliyuncs.com
15800983926
__
### 阿里云镜像中心
docker build -t registry.cn-hangzhou.aliyuncs.com/fijo_dev/e-box:22.09.08.01.ctl .
docker push registry.cn-hangzhou.aliyuncs.com/fijo_dev/e-box:22.09.08.01.ctl

### 清理镜像(一周清一次)：
docker image prune -a -f
docker container prune -f

