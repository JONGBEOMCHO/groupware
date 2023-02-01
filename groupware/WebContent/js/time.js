function getTime() {
		var d = new Date();	// 현재 날짜와 시간
		var year = d.getFullYear();
		var mon = d.getMonth()+1;
		var day = d.getDate();
		var weekday = new Array(7);
		weekday[0] = "일";
		weekday[1] = "월";
		weekday[2] = "화";
		weekday[3] = "수";
		weekday[4] = "목";
		weekday[5] = "금";
		weekday[6] = "토";
		var n = weekday[d.getDay()];
		var hur = d.getHours();		// 시
		var min = d.getMinutes();	//분
		var sec = d.getSeconds();	//초
		
		mon = mon < 10 ? `0${mon}` : mon;
		day = day < 10 ? `0${day}` : day;
		hur = hur < 10 ? `0${hur}` : hur;
		min = min < 10 ? `0${min}` : min;
		sec = sec < 10 ? `0${sec}` : sec;

		var dateBoard = document.getElementById("WhatDateIsItNow"); // 값이 입력될 공간
		var timeBoard = document.getElementById("WhatTimeIsItNow");
		
		var date = year+"-"+mon+"-"+day+"("+n+")"	// 형식 지정
		var time = +hur+":"+min+":"+sec
		
		dateBoard.innerHTML = date;	// 출력
		timeBoard.innerHTML = time;	// 출력
		
		setTimeout(getTime, 1000);	//1000밀리초(1초) 마다 반복
	}