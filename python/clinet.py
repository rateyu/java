#!/usr/bin/python
# -*- coding: UTF-8 -*-
# 文件名：client.py
 
import socket               # 导入 socket 模块
import time
 
s = socket.socket()         # 创建 socket 对象
host = '127.0.0.1'         # 获取本地主机名
port = 9998                # 设置端口号
 
s.connect((host, port))
var = 1
while var ==1 :
	s.send('hello world 中华人民共和国万岁 to server\n')
	print s.recv(1024)
	time.sleep(0.001)
#s.close()
#print 'socket end'
