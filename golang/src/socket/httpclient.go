package main

import (
	"fmt"
	"io/ioutil"
	"net"
	"net/http"
	"os"
	"time"
)

func testClient(name int){
	var i int

		conn,err := net.Dial("tcp","127.0.0.1:9998")
		if err!= nil {
			fmt.Println("dial fail")
			os.Exit(1)
		}

		defer conn.Close()

	for {
		fmt.Println(i)

		sendData := "hello world\n"
		//buffer []byte =[]byte("are you ok?")
		n,err := conn.Write([]byte(sendData))
		if err != nil{
			fmt.Println(err)
			return
		}
		fmt.Println("send hello",name)


		buffer := make ([]byte,512)
		n ,err2 := conn.Read(buffer)
		if err2 != nil {
			fmt.Println("read fail")
			return
		}

		fmt.Println("count:",n,"msg",string(buffer),name)

		//time.Sleep(time.Millisecond )
		time.Sleep(time.Microsecond )

		i++

	}
}

func httpGet(num2 int) {

	for  {

	resp, err := http.Get("http://127.0.0.1:8080/")
		// handle error
		if err != nil {
                fmt.Println(err)
		return
	}

	defer resp.Body.Close()
	body, err := ioutil.ReadAll(resp.Body)
		// handle error
		if err != nil {
		fmt.Println(err)
		return
	}

	fmt.Println(string(body),num2)
		time.Sleep(time.Millisecond )
	}
}

func main()  {
	//conn, err := net.Dial("tcp","127.0.0.1:9998")
/*	var num int
	for {
		fmt.Println(num)
		go testClient(num)
		num++
		if num>1000 {
			break
		}
	}
*/
	var num2 int
	for {
		//模拟http请求
		go httpGet(num2)
		num2++
		if num2>500 {
			break
		}

	}

	for {
		fmt.Println("i : runnging in main")
		time.Sleep(time.Second*30)
	}

}
