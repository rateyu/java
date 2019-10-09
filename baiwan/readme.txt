
使用awk命令，快速统计日志的每秒请求量
awk '{print $1}' output2.log | cut -c 1-8 | sort | uniq -c

