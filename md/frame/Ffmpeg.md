## Ffmpeg（http://ffmpeg.org/ffmpeg.html）

### 学习
1. 常见音视频概念(https://zhuanlan.zhihu.com/p/450972439)
   1. 容器/文件（ Conainer/File）：特定格式的多媒体文件，比如mp4、 flv、 mkv等
   2. 媒体流（ Stream）：表示时间轴上的一段连续数据，比如一段声音、一段视频或一段字幕；数据可以是压缩的，也可以是非压缩的，其中压缩的数据需要关联特定的编解码器。 
   3. 数据帧/数据包（Frame/Packet）：一般一个媒体流是由大量的数据帧组成的，对于压缩数据，帧对应着编解码器的最小处理单元，分属于不同媒体流的数据帧交错存储于容器之中。 
   4. 编解码器：编解码器是以帧为单位实现压缩数据和原始数据之间相互转换的
   5. 复用器：音频流、视频流、字母流以及其他成分，按照一定的规则组合成视频文件（MP4/flv）
   6. 解复用器：视频文件（MP4/flv）按照一定的规则拆分成，音频流、视频流、字母流以及其他成分
   7. 视频编解码器
   8. 音频编解码器
2. ffmpeg整体架构(https://zhuanlan.zhihu.com/p/456072886，https://github.com/0voice/ffmpeg_develop_doc/tree/main/%E6%96%87%E6%A1%A3%E5%BA%93)
   1. FFmpeg的封装模块AVFormat
   2. FFmpeg的编解码板块AVCodec
   3. FFmpeg的滤镜模块AVFilter
   4. FFmpeg的视频图像转换计算模块swscale
   5. FFmpeg的音频转换计算模块swresample
3. FFmpeg的编解码工具ffmpeg,ffmpeg是FFmpeg源代码编译后生成一个可执行程序，其可以作为命令行工具使用
   1. 转码：./ffmpeg -i input.mp4 (-f) output.avi
      1. -i 指定输入源input.mp4
      2. 转码
      3. -f 可省略，指定输出文件的容器格式
   2. 播放：ffplay
   3. 多媒体分析：ffprobe
   4. 截取音频:ffmpeg64.exe -i ***.ape -vn -acodec copy -ss 00:00:00 -t 00:01:32 output.ape
      1. -i：输入参数
      2. -acodec copy 重新编码并复制到新文件中
      3. -ss 开始时间点
      4. -t 截取音频时间长度
   5. 转换音频:ffmpeg64.exe -i ***.ape -ar 44100 -ac 2 -ab 16k -vok 50 -f ape **.ape
      1. -i：输入参数
      2. -acodec acc：音频编码用AAC
      3. -ar 色纸音频采样频率
      4. -ac 设置音频通道数
      5. -ab 设置声音比特率
      6. vol 设定音量（百分比）