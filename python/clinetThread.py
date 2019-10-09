#!/usr/bin/python
# -*- coding: UTF-8 -*-
# 文件名：client.py
 
import socket               # 导入 socket 模块
import time
import thread

def thread_start(threadName):

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
try:
	a =1000
	while a > 0:
		thread.start_new_thread(thread_start,(a,))
		a = a-1
#	thread.start_new_thread(thread_start,("thread2",))
except:
	print "Error: unable to start thread"

#time.sleep(10)
while 1:
	pass

