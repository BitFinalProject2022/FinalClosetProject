function getBotResponse(input) {

	if (input == "1" || input == "세탁방법") {
		return "린넨 / 면 / 데님 / 인견 / 셔츠 / 시폰 / 패딩 / 니트 / 스웨이트 / 캐시미어 / 모직코트 / 가디건 / 자켓 / 아웃도어"
	} else if (input == "2" || input == "보관방법") {
		return "패딩보관 / 가죽재킷보관 / 모직코트보관 / 니트보관 / 캐시미어보관 / 스웨이드보관"
	} else if (input == "3" || input == "퍼스널컬러") {
		return ("봄 - 라이트 / 브라이트<br>\
				여름 - 라이트 / 브라이트 / 뮤트<br>\
				가을 - 뮤트 / 스트롱 / 딥 <br>\
				겨울 - 브라이트 / 딥 <br><br>\
				ex) 봄 라이트")
	} else if (input == "4" || input == "키워드") {
		return "키워드<br>1.세탁방법<br>\
        2. 보관방법<br>\
        3. 퍼스널컬러";
	}

		if (input == "린넨") {
			return "30도 이하의 물에서 중성 세제를 이용해 손세탁해주는 것이 가장 좋습니다. \
			세탁기를 사용할 때는 세탁망을 이용해 30도 이하의 온도에서 ‘울 빨래’ 기능을 이용해 단독 세탁이 권장됩니다. ";
		} else if (input == "면") {
			return ". 30도 정도 되는 미지근한 물에 중성 세제를 풀어 부드럽게 세탁해주는 방법이 가장 좋습니다. \
			세탁기로 빨래를 할 때는 미온수로 세탁하면 변형을 막을 수 있습니다. \
			건조 시에는 옷걸이에 거는 거보다는 넓게 펼쳐서 건조하는 것이 목 늘어남을 방지할 수 있는 방법입니다.";
		} else if (input == "데님") {
			return "바지를 뒤집어 세탁망에 넣으면 옷의 변형을 줄일 수 있습니다. \
			데님은 특히 첫 세탁에 물 빠짐이 심하기 때문에 단독 세탁을 권장합니다.\
			가장 좋은 방법은 오염된 부분만 부분 세탁하는 것입니다. \
			전체 세탁을 할 경우 이염을 최소화하기 위해 찬물에서 세탁하는 것이 좋습니다. "
		} else if (input == "인견") {
			return " 오래 물에 담가 놓기 보다는 차가운 물로 빠르게 손세탁을 한 후 건조하는 것이 좋습니다. \
					세탁기를 사용할 경우 30도 이하의 찬물에 중성 세제를 이용해 줍니다. \
					이불 전용 세탁망에 넣어 ‘울 코스’로 세탁해 준다면 변형을 막을 수 있습니다.";
		} else if (input == "셔츠") {
			return "세탁기를 돌려도 깃과 소매 부분에 얼룩이나 때가 있다면 샴푸를 물에 풀고 그 부분을 손으로 빨거나, 부드러운 솔로 닦으면 지워진다."
		} else if (input == "시폰") {
			return "시폰은 물세탁을 하면 미어지는 현상이 있기 때문에 물빨래보다는 드라이클리닝을 맡기는 것이 좋다."
		} else if (input == "패딩") {
			return "30도 정도 미지근한 물에 중성세제를 푼 후 지퍼와 단추를 모두 잠그고 빨아야 한다.\
			패딩류를 세탁할 때 섬유유연제는 옷의 기능을 떨어트릴 수 있기 때문에 가급적 사용하지 않는 것이 좋다."
		} else if (input == "니트") {
			return "30도 정도의 미지근한 물에 중성세제 또는 니트 전용 세제를 풀어 5분 정도만 주무르듯이 세탁해야 한다.\
			이때 식초나 레몬즙을 살짝 넣어주면 보풀 발생을 예방 할 수 있다.\
			니트를 말릴 때 옷걸이 등에 걸면 옷이 늘어지는 등 변형이 생길 수 있으니 바닥이 평평한 그늘아래에서 말리는 것이 좋다."
		} else if (input == "스웨이드") {
			return "소재가 까다롭기 때문에 직접적인 물 세탁은 피하고 집에서 세탁하기 보다는 전문 클리닝을 맡겨 세탁하는 것이 좋다."
		} else if (input == "캐시미어") {
			return "캐시미어는 드라이클리닝을 하거나 울 샴푸나 중성세제를 미지근한 물에 풀어서 니트류와 똑같이 손빨래를 한다.\
			말릴 때 물기를 꽉 짜거나 옷을 비틀면 옷에 변형이 생길 수 있기 때문에 수건으로 물기를 제거한 후\
			그늘에 뉘어서 원래 형태를 유지하며 말려야 한다."
		} else if (input == "모직코트") {
			return "자주 세탁하는 것보다 겨울이 끝날 때 쯤 한 번 드라이클리닝을 해주는 것이 좋다.\
			옷에 냄새가 배었다면 섬유 탈취제를 뿌려 걸어두어 냄새를 뺀다."
		} else if (input == "가디건") {
			return "가디건은 울샴푸를 넣고 손빨래를 한 후 수건을 이용하여 물기를 뺀다.\
			건조할 때는 옷걸이보다 바람이 잘 통하는 곳에 펼쳐서 말려주는 것이 좋다."
		} else if (input == "자켓") {
			return "드라이클리닝을 맡기는 것이 좋다.\
			만약 옷에 무언가 살짝 묻어서 급하게 세탁해야 한다면 물이나 퐁퐁을 사용하여 그 부분의 얼룩을 지워준다."
		} else if (input == "아웃도어") {
			return "아웃도어 재킷 등에 사용되는 고어텍스 소재는 드라이클리닝을 하면 막이 손상되기 때문에 미지근한 물에 손세탁을 하는 것이 좋다."
		}

	if (input == "패딩보관" || input == "패딩 보관") {
		return "패딩을 옷걸이에 그냥 걸어두면 충전재가 아래로 쏠려 모양이 망가질 수 있다.\
					패딩의 모든 지퍼를 위로 올린 후 양쪽 소매를 안쪽으로 접어 부피를 줄인다.\
					목 부분부터 공기를 빼면서 돌돌 말아 끈으로 묶어서 보관한다."
	} else if (input == "가죽재킷보관" || input == "가죽재킷 보관" || input == "가죽재킷 보관" || input == "가죽재킷 보관") {
		return "가죽재킷은 통풍이 잘 되는 곳에 보관을 해주어야 한다.\
					습기를 막기 위해 옷 사이사이에 신문지를 넣어주는 것이 좋다.\
					습기제거제를 사용하면 가죽이 딱딱하게 변할 수 있으니 습기제거제 사용은 주의해야 한다."
	} else if (input == "모직코트보관" || input == "모직코트 보관") {
		return "모직코트는 외출 후 옷솔로 먼지를 털어준 후 보관을 해야 한다.\
            		보관시에는 전용 커버를 씌워 나무 소재의 옷걸이에 걸어 옷장에 보관을 하고, 전용 커버가 없다면 안 입는 셔츠나 티셔츠를 덮어준다."
	} else if (input == "캐시미어보관" || input == "캐시미어 보관") {
		return "캐시미어로 된 옷을 입을 땐, 한 번 입은 후 2~3일의 휴식기를 주어야 옷을 오래 입을 수 있다."
	} else if (input == "스웨이드보관" || input == "스웨이드 보관") {
		return "스웨이드 재질의 옷감은 그늘진 곳에 방충와 방습제를 같이 넣어 보관해야 한다.\
					습기제거제와 방충제를 넣어두면 세균과 곰팡이의 번식을 막을 수 있다."
	} else if (input == "니트보관" || input == "니트 보관") {
		return "니트는 옷걸이에 걸면 늘어날 수 있기 때문에 바르게 접어나 돌돌 말아서 장롱이나 상자에 넣어서 보관해야 한다.\
					자국이 남는 것을 방지하기 위해 신문지를 등판에 댄 후 접으면 자국이 생기는 것을 막을 수 있다."
	}

	if (input == "봄라이트" || input == "봄 라이트") {
		var img11 = document.createElement('div');
		img11.innerHTML = "<img src='img/봄라이트.png' width='250' onclick= window.open(this.src) >"
		return img11.innerHTML;
	} else if (input == "봄브라이트" || input == "봄 브라이트") {
		var img22 = document.createElement('div');
		img22.innerHTML = "<img src='img/봄브라이트.png' width='200' onclick= window.open(this.src) > "
		return img22.innerHTML;
	} else if (input == "여름라이트" || input == "여름 라이트") {
		var img33 = document.createElement('div');
		img33.innerHTML = "<img src='img/여름라이트.png' width='200' onclick= window.open(this.src) >"
		return img33.innerHTML;
	} else if (input == "여름브라이트" || input == "여름 브라이트") {
		var img44 = document.createElement('div');
		img44.innerHTML = "<img src='img/여름브라이트.png' width='200' onclick= window.open(this.src) >"
		return img44.innerHTML;
	} else if (input == "여름뮤트" || input == "여름 뮤트") {
		var img55 = document.createElement('div');
		img55.innerHTML = "<img src='img/여름뮤트.png' width='200' onclick= window.open(this.src) >"
		return img55.innerHTML;
	} else if (input == "가을뮤트" || input == "가을 뮤트") {
		var img66 = document.createElement('div');
		img66.innerHTML = "<img src='img/가을뮤트.png' width='200' onclick= window.open(this.src) >"
		return img66.innerHTML;
	} else if (input == "가을스트롱" || input == "가을 스트롱") {
		var img77 = document.createElement('div');
		img77.innerHTML = "<img src='img/가을스트롷.png' width='200' onclick= window.open(this.src) >"
		return img77.innerHTML;
	} else if (input == "가을딥" || input == "가을 딥") {
		var img88 = document.createElement('div');
		img88.innerHTML = "<img src='img/가을딥.png' width='200' onclick= window.open(this.src) >"
		return img88.innerHTML;
	} else if (input == "겨울브라이트" || input == "겨울 브라이트") {
		var img99 = document.createElement('div');
		img99.innerHTML = "<img src='img/겨울브라이트.png' width='200' onclick= window.open(this.src) >"
		return img99.innerHTML;
	} else if (input == "겨울딥" || input == "겨울 딥") {
		var img00 = document.createElement('div');
		img00.innerHTML = "<img src='img/겨울딥.png' width='200' onclick= window.open(this.src) >"
		return img00.innerHTML;
	}




	if (input == "규원") {
		return "안녕하세요 반갑습니다";
	} else if (input == "광훈") {
		var img2 = document.createElement('div');
		img2.innerHTML = "<img src='img/gwang.jpg' width='200' onclick= window.open(this.src) >"
		return img2.innerHTML;
	} else if (input == "경섭") {
		var img3 = document.createElement('div');
		img3.innerHTML = "<img src='img/sub.jpg' width='200' onclick= window.open(this.src) >"
		return img3.innerHTML;
	} else if (input == "태은") {
		var img4 = document.createElement('div');
		img4.innerHTML = "<img src='img/taeny.jpg' width='200' onclick= window.open(this.src) >"
		return img4.innerHTML;
	} else if (input == "김채원") {
		var img4 = document.createElement('div');
		img4.innerHTML = "<img src='img/chaewon.jpg' width='200' onclick= window.open(this.src) >"
		return img4.innerHTML;
	} else if (input == "안녕") {
		return "안녕하세요 반갑습니다";
	} else if (input == "주희") {
		return "우하-하-하-하-하"
	} else if (input == "상준") {
		return "담배피는남자 어떤데"
	} else if (input == "경섭님") {
		return "무슨일이시죠?"
	} else if (input == "이거어때요?") {
		return "최악이야"
	} else if (input == "죄송해요") {
		return "그럴수있지"
	} else if (input == "아쉽다") {
		return "까비"
	} else {
		return "죄송해요";
	}

}
