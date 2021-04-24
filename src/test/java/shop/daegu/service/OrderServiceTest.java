package shop.daegu.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import shop.daegu.dto.excel.ExcelToOrder;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    private ExcelToOrder excelToOrder;

    @BeforeEach
    void beforeEach() {
        //excelToOrder = ExcelToOrder(orderDate=2021-04-24, dataList=[ExcelData(clientName=구미-농심공장, itemName=냉동)포켓치킨(양념)30입, count=1, size=박스, price=53000), ExcelData(clientName=구미-농심공장, itemName=냉장)롯데)의성마늘후랑크 70G*30, count=1, size=박스, price=21000), ExcelData(clientName=구미-농심공장, itemName=냉장)대림)대림후랑크 1200*30, count=1, size=박스, price=17000), ExcelData(clientName=구미-농심공장, itemName=냉장)한성)매콤후랑크 800*80G*50(인상), count=2, size=박스, price=21000), ExcelData(clientName=구미-농심공장, itemName=사조)꼬마장사(치즈킹)(통) 70G*20, count=1, size=통, price=8500), ExcelData(clientName=구미-농심공장, itemName=비)600미니벨(통) 20입, count=1, size=통, price=6500), ExcelData(clientName=구미-농심공장, itemName=해태제과)자유시간(리얼초코)(통) 1000*12, count=2, size=통, price=6600), ExcelData(clientName=구미-농심공장, itemName=롯데제과)아몬드초코볼(통) 2000*10, count=2, size=통, price=11000), ExcelData(clientName=구미-농심공장, itemName=비)500킷캣 24입(통), count=1, size=통, price=8400), ExcelData(clientName=구미-농심공장, itemName=비)500감자알칩 500*40, count=1, size=박스, price=12000), ExcelData(clientName=구미-농심공장, itemName=크라운제과)초코하임 1400*18, count=1, size=박스, price=13104), ExcelData(clientName=구미-농심공장, itemName=해태제과)에이스 4500*10, count=1, size=박스, price=23400), ExcelData(clientName=구미-농심공장, itemName=롯데제과)칙촉(더블초코) 2400*20, count=1, size=박스, price=24000), ExcelData(clientName=구미-농심공장, itemName=농심)칩포테토(오리지날) 20입, count=1, size=박스, price=19000), ExcelData(clientName=구미-농심공장, itemName=롯데제과)치토스(매콤) 1500*16, count=1, size=박스, price=13200), ExcelData(clientName=구미-농심공장, itemName=오리온)오징어땅콩 1500*16, count=1, size=박스, price=16500), ExcelData(clientName=구미-농심공장, itemName=매)피크닉(사과) 200ML*24, count=1, size=박스, price=5600), ExcelData(clientName=구미-농심공장, itemName=매)피크닉(청포도) 200ML*24, count=1, size=박스, price=5600), ExcelData(clientName=다솔(윤민수), itemName=빙그레)바나나우유(박스) 240ML*32입, count=1, size=박스, price=24000), ExcelData(clientName=다솔(윤민수), itemName=대신)한맥)1000불벅(박스) 30입, count=1, size=박스, price=24000), ExcelData(clientName=다솔(윤민수), itemName=대신)한맥)1000마시따(박스) 30입, count=1, size=박스, price=23500), ExcelData(clientName=다솔(윤민수), itemName=대신)파)냉동)직화불고기버거(박스) 30입, count=1, size=박스, price=0), ExcelData(clientName=다솔(윤민수), itemName=대신)파)냉동)골드타임(박스) 30입, count=1, size=박스, price=0), ExcelData(clientName=다솔(윤민수), itemName=대신)한맥)냉동)1200냉동불벅(박스) 30입, count=1, size=박스, price=0), ExcelData(clientName=다솔(윤민수), itemName=냉동)포켓치킨(데리야끼)30입, count=1, size=박스, price=0), ExcelData(clientName=다솔(윤민수), itemName=냉동)포켓치킨(양념)30입, count=1, size=박스, price=0), ExcelData(clientName=다솔(윤민수), itemName=냉동)비)라체나(불고기) 1600*100G*30, count=1, size=박스, price=0), ExcelData(clientName=다솔(윤민수), itemName=냉동)비)라체나(콤비네이션) 1600*100G*30, count=1, size=박스, price=26000), ExcelData(clientName=다솔(윤민수), itemName=냉장)대림)대림후랑크 1200*30, count=1, size=박스, price=15500), ExcelData(clientName=다솔(윤민수), itemName=냉장)롯데)의성마늘후랑크 70G*30, count=2, size=박스, price=19500), ExcelData(clientName=다솔(윤민수), itemName=비)500킷캣 24입(통), count=1, size=통, price=8400), ExcelData(clientName=다솔(윤민수), itemName=비)700브이콘(인상)*40, count=5, size=박스, price=16000), ExcelData(clientName=다솔(윤민수), itemName=매)피크닉(사과) 200ML*24, count=2, size=박스, price=5500), ExcelData(clientName=영진직업전문학교, itemName=빙그레)바나나우유(박스) 240ML*32입, count=1, size=박스, price=24320), ExcelData(clientName=영진직업전문학교, itemName=대신)파)1000맛스타(박스) 40입, count=1, size=박스, price=28000), ExcelData(clientName=영진직업전문학교, itemName=삼립)주종단팥크림빵(낱개) 1000*1, count=10, size=낱개, price=780), ExcelData(clientName=영진직업전문학교, itemName=삼립)롱파운드케익(낱개) 1000*1, count=10, size=낱개, price=780), ExcelData(clientName=영진직업전문학교, itemName=삼립)빅패스츄리(낱개) 1000*1, count=10, size=낱개, price=780), ExcelData(clientName=영진직업전문학교, itemName=삼립)카스타드소보루(낱개) 1000*1, count=10, size=낱개, price=780), ExcelData(clientName=영진직업전문학교, itemName=삼립)롱파운드케익(박스) 15입, count=1, size=박스, price=11400), ExcelData(clientName=영진직업전문학교, itemName=삼립)옥수수크림빵(낱개) 1000*1, count=10, size=낱개, price=780), ExcelData(clientName=영진직업전문학교, itemName=대신)파)냉동)치즈브래드(박스) 30입, count=1, size=박스, price=22500), ExcelData(clientName=영진직업전문학교, itemName=오뚜기)치즈볶이(대)12입 1200*12, count=1, size=박스, price=9300), ExcelData(clientName=영진직업전문학교, itemName=오뚜기)참깨컵(대)12입 1200*12, count=1, size=박스, price=8500), ExcelData(clientName=영진직업전문학교, itemName=오뚜기)김치면(대) 12입, count=1, size=박스, price=7500), ExcelData(clientName=영진직업전문학교, itemName=오뚜기)라면볶이(대) 12입, count=1, size=박스, price=7800), ExcelData(clientName=영진직업전문학교, itemName=오뚜기)스파게티(대) 12입, count=1, size=박스, price=7500), ExcelData(clientName=영진직업전문학교, itemName=오뚜기)열라면(대) 12입, count=2, size=박스, price=7500), ExcelData(clientName=영진직업전문학교, itemName=크라운제과)마이쮸(사과)(통) 800*15, count=1, size=통, price=6600), ExcelData(clientName=영진직업전문학교, itemName=크라운제과)마이쮸(포도)(통) 800*15, count=1, size=통, price=6600), ExcelData(clientName=영진직업전문학교, itemName=매)바리스타(모카프레소) 250ML*10, count=2, size=박스, price=12500), ExcelData(clientName=영진직업전문학교, itemName=매)바리스타(에스프레소라떼) 250ML*10, count=2, size=박스, price=12500), ExcelData(clientName=영진직업전문학교, itemName=롯데음료)밀키스(빵) 350ML*24, count=2, size=박스, price=12000), ExcelData(clientName=영진직업전문학교, itemName=코카)코카콜라(빵) 355ML*24, count=2, size=박스, price=17000), ExcelData(clientName=좋은날, itemName=대신)파)1000맛스타(박스) 40입, count=3, size=박스, price=28000), ExcelData(clientName=좋은날, itemName=대신)파)1000치즈브래드(박스) 40입, count=2, size=박스, price=28000), ExcelData(clientName=좋은날, itemName=대신)파)1000핫치킨바베큐(박스) 40입, count=2, size=박스, price=28000), ExcelData(clientName=좋은날, itemName=대신)파)1000시카고피자(박스) 30입, count=3, size=박스, price=21000), ExcelData(clientName=좋은날, itemName=삼립)허쉬초코롤(박스) 24입, count=3, size=박스, price=17280), ExcelData(clientName=좋은날, itemName=삼립)미스터쉐프치즈(박스) 16입, count=2, size=박스, price=11520), ExcelData(clientName=좋은날, itemName=삼립)우카빵(박스) 12입, count=1, size=박스, price=8640), ExcelData(clientName=좋은날, itemName=삼립)카스타드소보루(박스) 20입, count=1, size=박스, price=14400), ExcelData(clientName=좋은날, itemName=삼립)핫더블소세지(박스) 27입, count=1, size=박스, price=19440), ExcelData(clientName=좋은날, itemName=냉장)롯데)의성마늘후랑크 70G*30, count=6, size=박스, price=19000), ExcelData(clientName=1)경주고, itemName=빙그레)딸기타임우유(박스) 500ML*28입, count=3, size=박스, price=16800), ExcelData(clientName=1)경주고, itemName=빙그레)바나나우유(낱개) 1300*1, count=15, size=낱개, price=850), ExcelData(clientName=1)경주고, itemName=빙그레)초코타임우유(박스) 500ML*28입, count=4, size=박스, price=16800), ExcelData(clientName=1)경주고, itemName=대신)파)냉동)치즈브래드(박스) 30입, count=1, size=박스, price=23100), ExcelData(clientName=1)경주고, itemName=냉동)포켓치킨(데리야끼)30입, count=1, size=박스, price=52000), ExcelData(clientName=1)경주고, itemName=냉동)로만)양심치킨 1800*24, count=2, size=박스, price=26000), ExcelData(clientName=1)경주고, itemName=냉장)사조)남부불고기맛후랑크 70G*30, count=2, size=박스, price=18000), ExcelData(clientName=1)경주고, itemName=안주)철판구이오징어18G* 20입, count=2, size=봉지, price=16200), ExcelData(clientName=1)경주고, itemName=비)1000추억의어포 10입(통), count=1, size=통, price=6000), ExcelData(clientName=1)경주고, itemName=비)500킷캣 24입(통), count=3, size=통, price=8400), ExcelData(clientName=1)경주고, itemName=롯데제과)껌)왓따껌(가나)(통) 500*16, count=2, size=통, price=5200), ExcelData(clientName=1)경주고, itemName=롯데제과)구미구미(시트러스) 1000*12 (통), count=1, size=통, price=7200), ExcelData(clientName=1)경주고, itemName=롯데제과)구미구미 1000*12 (통), count=1, size=통, price=7200), ExcelData(clientName=1)경주고, itemName=롯데제과)롤리팝 250*60(통), count=1, size=통, price=9750), ExcelData(clientName=1)경주고, itemName=크라운제과)마이쮸(포도)(통) 800*15, count=2, size=통, price=6600), ExcelData(clientName=1)경주고, itemName=크라운제과)마이쮸(사과)(통) 800*15, count=2, size=통, price=6600), ExcelData(clientName=1)경주고, itemName=크라운제과)새콤달콤(포도)(통) 500*15, count=2, size=통, price=4125), ExcelData(clientName=1)경주고, itemName=비)500큰소리뻥(인상) 500*40, count=2, size=박스, price=12000), ExcelData(clientName=1)경주고, itemName=해태제과)구운오징어 1500*16, count=1, size=박스, price=14400), ExcelData(clientName=1)경주고, itemName=해태제과)구운양파 1500*16입, count=1, size=박스, price=14400), ExcelData(clientName=1)경주고, itemName=해태제과)구운대파 1500*16입, count=1, size=박스, price=14400), ExcelData(clientName=1)경주고, itemName=롯데제과)도리토스(갈비천황) 1500*16입, count=1, size=박스, price=14400), ExcelData(clientName=1)경주고, itemName=롯데제과)비타C박스 1000*12*4, count=1, size=박스, price=28800), ExcelData(clientName=1)경주고, itemName=롯데제과)치토스(매콤) 1500*16, count=1, size=박스, price=14400), ExcelData(clientName=1)경주고, itemName=롯데제과)쬰쬬니(청포도) 500*25*6, count=1, size=박스, price=45000), ExcelData(clientName=1)경주고, itemName=롯데제과)ABC초코쿠키 1000*32, count=1, size=박스, price=19200), ExcelData(clientName=1)경주고, itemName=롯데제과)ABC초코렛 2000*20입, count=1, size=박스, price=24000), ExcelData(clientName=1)경주고, itemName=롯데제과)꼬깔콘(군옥수수) 1500*20입, count=1, size=박스, price=18000), ExcelData(clientName=1)경주고, itemName=롯데제과)도리토스(나쵸치즈) 1500*16입, count=2, size=박스, price=14400), ExcelData(clientName=1)경주고, itemName=농심)1000쫄병스낵(매콤한맛) 30입, count=1, size=박스, price=21000), ExcelData(clientName=1)경주고, itemName=매)엔요(플레인) 200ML*24, count=1, size=박스, price=9800), ExcelData(clientName=1)경주고, itemName=매)피크닉(사과) 200ML*24, count=3, size=박스, price=6000), ExcelData(clientName=1)경주고, itemName=음료)뽀로로(밀크) 235*24입, count=5, size=박스, price=15500), ExcelData(clientName=1)경주고, itemName=해태음료)썬)(오렌지드링크)캔30입, count=5, size=박스, price=7000), ExcelData(clientName=1)경주고, itemName=해태음료)썬)(머스켓드링크)캔30입, count=5, size=박스, price=7000), ExcelData(clientName=1)경주고, itemName=해태음료)스프라이트(빵) 350ML*24입, count=5, size=박스, price=13000), ExcelData(clientName=1)경주고, itemName=음료)연호)한라봉(빵) 355ML*24입, count=5, size=박스, price=12000), ExcelData(clientName=1)경주고, itemName=음료)엔톡(복숭아) 340ML*24입, count=5, size=박스, price=13000), ExcelData(clientName=1)경주고, itemName=음료)브이톡(블루레몬에이드) 350ML*24입, count=3, size=박스, price=13000), ExcelData(clientName=1)경주고, itemName=비)썬라이트(사과)인상 48입, count=3, size=박스, price=0), ExcelData(clientName=1)경주고, itemName=비)썬라이트(망고)인상 48입, count=3, size=박스, price=0), ExcelData(clientName=1)신라고, itemName=대신)파)1000맛스타(박스) 40입, count=1, size=박스, price=32000), ExcelData(clientName=1)신라고, itemName=대신)파)냉동)직화불고기버거(박스) 30입, count=1, size=박스, price=35000), ExcelData(clientName=1)신라고, itemName=냉동)산동만두(매운맛)30입 30P, count=4, size=박스, price=26000), ExcelData(clientName=1)신라고, itemName=냉장)대림)대림후랑크 1200*30, count=3, size=박스, price=18000), ExcelData(clientName=1)신라고, itemName=삼립1)트)미니약과(1000G)(봉) 7000*1, count=1, size=봉, price=6000), ExcelData(clientName=1)신라고, itemName=오리온)왕꿈틀이(오리지널)(통) 1000*10, count=2, size=통, price=7000), ExcelData(clientName=1)신라고, itemName=오리온)새알(통) 1000*12, count=2, size=통, price=8400), ExcelData(clientName=1)신라고, itemName=비)500킷캣 24입(통), count=1, size=통, price=8400), ExcelData(clientName=1)신라고, itemName=비)500매운콘칩 500*30, count=1, size=박스, price=9000), ExcelData(clientName=1)신라고, itemName=비)500매콤한포테이토칩 40입, count=1, size=박스, price=12000), ExcelData(clientName=1)신라고, itemName=비)1000매콤달콤치즈스틱스낵 20입, count=1, size=박스, price=12000), ExcelData(clientName=1)신라고, itemName=오뚜기)뿌셔뿌셔(양념치킨) 24입, count=1, size=박스, price=12300), ExcelData(clientName=1)신라고, itemName=롯데제과)미니초코칩쿠키 1200*12*4, count=1, size=박스, price=34560), ExcelData(clientName=1)신라고, itemName=오리온)초코송이 1000*24, count=1, size=박스, price=16800), ExcelData(clientName=1)신라고, itemName=농심)닭다리(후라이드) 20입, count=1, size=박스, price=0), ExcelData(clientName=1)신라고, itemName=동아)나랑드(P/T) 500ML*20, count=1, size=박스, price=12500), ExcelData(clientName=1)신라고, itemName=동아)포카리(빵) 340ML*24입, count=1, size=박스, price=13500), ExcelData(clientName=1)신라고, itemName=음료)복숭아녹차 350ML*20, count=1, size=박스, price=13000), ExcelData(clientName=1)신라고, itemName=음료)웰치스(포도) 1000*24, count=1, size=박스, price=14000), ExcelData(clientName=1)가정편의점, itemName=롯데,삼립)섞어빵 1000*1, count=10, size=낱개, price=800), ExcelData(clientName=1)가정편의점, itemName=삼립)간식)정통크림빵 24입, count=1, size=박스, price=20400), ExcelData(clientName=1)가정편의점, itemName=파인)망사계란 3입*10줄, count=1, size=판, price=9000), ExcelData(clientName=1)가정편의점, itemName=비)300본오본(초코)(갈색)(통) 30입, count=3, size=통, price=5400), ExcelData(clientName=1)가정편의점, itemName=팔도)꼬꼬면컵 1300*16, count=1, size=박스, price=15000), ExcelData(clientName=1)가정편의점, itemName=오뚜기)부대찌개라면컵(대) 12입, count=1, size=박스, price=13500), ExcelData(clientName=1)가정편의점, itemName=비)단추오란다 50G*40입, count=1, size=박스, price=0), ExcelData(clientName=1)가정편의점, itemName=삼립1)1000누네띠네 30입(인상), count=2, size=박스, price=15000), ExcelData(clientName=1)가정편의점, itemName=비)1000삿뽀로하면스낵(싱싱) 20입, count=1, size=박스, price=12500), ExcelData(clientName=1)가정편의점, itemName=삼립1)1200구운미니마늘바게트 12입, count=1, size=박스, price=9900), ExcelData(clientName=1)가정편의점, itemName=해태제과)오사쯔 1500*16, count=1, size=박스, price=15600), ExcelData(clientName=1)가정편의점, itemName=오리온)오징어땅콩 1500*16, count=1, size=박스, price=16800), ExcelData(clientName=1)가정편의점, itemName=농심)1000쫄병스낵(바베큐맛) 30입, count=1, size=박스, price=22000), ExcelData(clientName=1)가정편의점, itemName=조지아)크래프트(라떼) 470ML*24입, count=1, size=박스, price=21600), ExcelData(clientName=1)가정편의점, itemName=음료)레몬녹차 350ML*20, count=1, size=박스, price=13800), ExcelData(clientName=1)가정편의점, itemName=음료)엔톡(청포도) 350ML*24입, count=1, size=박스, price=0), ExcelData(clientName=1)경주대본관, itemName=삼립)롱파운드케익(박스) 15입, count=1, size=박스, price=12900), ExcelData(clientName=1)경주대본관, itemName=삼립)미니치즈후레쉬팡(낱개) 1000*1, count=5, size=낱개, price=860), ExcelData(clientName=1)경주대본관, itemName=삼립)미스터쉐프치즈(낱개) 1000*1, count=5, size=낱개, price=860), ExcelData(clientName=1)경주대본관, itemName=삼립)아시나요(박스) 24입, count=1, size=박스, price=20640), ExcelData(clientName=1)경주대본관, itemName=삼립)카스타드소보루(낱개) 1000*1, count=5, size=낱개, price=860), ExcelData(clientName=1)경주대본관, itemName=삼립)아시나요(낱개) 1000*1, count=6, size=낱개, price=860), ExcelData(clientName=1)경주대본관, itemName=대신)한맥)냉동)마시따 30입, count=1, size=박스, price=26000), ExcelData(clientName=1)경주대본관, itemName=냉장)한성)매콤후랑크 800*80G*50(인상), count=1, size=박스, price=20500), ExcelData(clientName=1)경주대본관, itemName=냉장)대림)대림후랑크 1200*30, count=1, size=박스, price=19500), ExcelData(clientName=1)경주대본관, itemName=팔도)왕뚜껑(김치) 1050*18, count=1, size=박스, price=15900), ExcelData(clientName=1)경주대본관, itemName=팔도)왕뚜껑(짬뽕) 1050*18, count=1, size=박스, price=15900), ExcelData(clientName=1)경주대본관, itemName=비)700브이콘(인상)*40, count=1, size=박스, price=17500), ExcelData(clientName=1)경주대본관, itemName=음료)비타500(100ML)(통) 600*10, count=3, size=통, price=4000), ExcelData(clientName=1)경주대본관, itemName=음료)레몬녹차 350ML*20, count=1, size=박스, price=13000), ExcelData(clientName=1)경주대본관, itemName=동아)박카스F 120ML*10*5, count=1, size=박스, price=28500), ExcelData(clientName=제이유통, itemName=냉동)포켓치킨(데리야끼)30입, count=30, size=박스, price=44550), ExcelData(clientName=제이유통, itemName=냉동)포켓치킨(양념)30입, count=30, size=박스, price=44550), ExcelData(clientName=제이유통, itemName=푸르밀)복숭아티(캔) 240ML*30, count=10, size=박스, price=6000), ExcelData(clientName=제이유통, itemName=푸르밀)망고코코넛 240ML*30, count=10, size=박스, price=6000), ExcelData(clientName=포항-1번, itemName=대신,햄버거)섞어버거 1000*1, count=80, size=낱개, price=660), ExcelData(clientName=포항-1번, itemName=삼립)미스터쉐프치즈(박스) 16입, count=1, size=박스, price=11680), ExcelData(clientName=포항-1번, itemName=삼립)빅스위트데니쉬(박스) 20입, count=1, size=박스, price=14600), ExcelData(clientName=포항-1번, itemName=삼립)빅패스츄리(박스) 14입, count=1, size=박스, price=10220), ExcelData(clientName=포항-1번, itemName=삼립)아시나요(박스) 24입, count=1, size=박스, price=17520), ExcelData(clientName=포항-1번, itemName=삼립)주종단팥크림빵(박스) 20입, count=1, size=박스, price=14600), ExcelData(clientName=포항-1번, itemName=삼립)카스타드소보루(박스) 20입, count=1, size=박스, price=14600), ExcelData(clientName=포항-1번, itemName=삼립)허쉬초코롤(박스) 24입, count=1, size=박스, price=17520), ExcelData(clientName=포항-1번, itemName=삼립)꿀떡꿀떡(박스) 24입, count=1, size=박스, price=17520), ExcelData(clientName=포항-1번, itemName=삼립)촉촉카스테라(박스) 18입, count=1, size=박스, price=13140), ExcelData(clientName=포항-1번, itemName=삼립)땅콩미니샌드(박스) 20입, count=1, size=박스, price=14600), ExcelData(clientName=포항-1번, itemName=대신)파)냉동)내가맛스타(박스) 30입, count=1, size=박스, price=21000), ExcelData(clientName=포항-1번, itemName=대신)파)냉동)직화불고기버거(박스) 30입, count=1, size=박스, price=32000), ExcelData(clientName=포항-1번, itemName=냉장)한성)매콤후랑크 800*80G*50(인상), count=3, size=박스, price=18500), ExcelData(clientName=포항-1번, itemName=냉장)대림)대림후랑크 1200*30, count=1, size=박스, price=16000), ExcelData(clientName=포항-1번, itemName=냉장)사조)남부불고기맛후랑크 70G*30, count=3, size=박스, price=12500), ExcelData(clientName=포항-1번, itemName=비)500꼬미볼(소다)(통) 500*20, count=2, size=통, price=5000), ExcelData(clientName=포항-1번, itemName=비)투데이도넛(딸기)(통) 12입, count=2, size=통, price=3240), ExcelData(clientName=포항-1번, itemName=비)백제 쌀국수(김치) 1400*30, count=2, size=박스, price=25000), ExcelData(clientName=포항-1번, itemName=비)백제 쌀국수(멸치) 1400*30, count=2, size=박스, price=25000), ExcelData(clientName=포항-1번, itemName=오뚜기)스파게티(대) 12입, count=3, size=박스, price=7500), ExcelData(clientName=포항-1번, itemName=오뚜기)김치면(대) 12입, count=3, size=박스, price=7500), ExcelData(clientName=포항-1번, itemName=비)500감자알칩 500*40, count=2, size=박스, price=10400), ExcelData(clientName=포항-1번, itemName=비)500꼬불이 60입, count=2, size=박스, price=15400), ExcelData(clientName=포항-1번, itemName=비)1000정성쫀디기 20입, count=5, size=박스, price=10000), ExcelData(clientName=포항-1번, itemName=비)프레첼(체다치즈) 85G*18입, count=1, size=박스, price=0), ExcelData(clientName=포항-1번, itemName=비)1200브이콘 1200*20, count=3, size=박스, price=15600), ExcelData(clientName=포항-1번, itemName=비)700브이콘(인상)*40, count=2, size=박스, price=15000), ExcelData(clientName=포항-1번, itemName=롯데제과)빈츠 2400*20, count=1, size=박스, price=24000), ExcelData(clientName=포항-1번, itemName=푸르밀)카페베네(모카)(흰) 200ML*10, count=1, size=박스, price=7500), ExcelData(clientName=포항-1번, itemName=푸르밀)카페베네(카라멜마끼)(흰) 200ML*10, count=1, size=박스, price=7500), ExcelData(clientName=포항-1번, itemName=푸르밀)카페베네(라떼)(흰) 200ML*10, count=1, size=박스, price=7500), ExcelData(clientName=포항-1번, itemName=음료)복숭아녹차 350ML*20, count=5, size=박스, price=10500), ExcelData(clientName=포항-1번, itemName=동아)박카스F 120ML*10*5, count=2, size=박스, price=28500), ExcelData(clientName=유가유통(매출), itemName=빙그레)바나나우유(박스) 240ML*32입, count=8, size=박스, price=23040), ExcelData(clientName=유가유통(매출), itemName=대신)파)1000치즈브래드(박스) 40입, count=7, size=박스, price=26000), ExcelData(clientName=유가유통(매출), itemName=대신)파)냉동)치즈브래드(박스) 30입, count=10, size=박스, price=20600), ExcelData(clientName=유가유통(매출), itemName=대신)파)냉동)직화불고기버거(박스) 30입, count=5, size=박스, price=32000), ExcelData(clientName=유가유통(매출), itemName=냉동)로만)크로크무슈 18입, count=10, size=박스, price=15300), ExcelData(clientName=계성고, itemName=빙그레)바나나우유(박스) 240ML*32입, count=2, size=박스, price=25600), ExcelData(clientName=계성고, itemName=빙그레)초코타임우유(박스) 500ML*28입, count=1, size=박스, price=15400), ExcelData(clientName=계성고, itemName=빙그레)딸기타임우유(박스) 500ML*28입, count=2, size=박스, price=15400), ExcelData(clientName=계성고, itemName=빙그레)흰우유(박스)200ML*40입, count=1, size=박스, price=0), ExcelData(clientName=계성고, itemName=롯데빵)꼬마꿀호떡(박스) 16입, count=1, size=박스, price=11360), ExcelData(clientName=계성고, itemName=삼립)미니치즈후레쉬팡(박스) 13입, count=1, size=박스, price=10400), ExcelData(clientName=계성고, itemName=삼립)빅딸기샌드(박스) 24입, count=1, size=박스, price=19200), ExcelData(clientName=계성고, itemName=삼립)허쉬초코롤(박스) 24입, count=1, size=박스, price=19200), ExcelData(clientName=계성고, itemName=대신)파)냉동)치즈브래드(박스) 30입, count=1, size=박스, price=22500), ExcelData(clientName=계성고, itemName=냉동)포켓치킨(데리야끼)30입, count=1, size=박스, price=50500), ExcelData(clientName=계성고, itemName=냉동)포켓치킨(양념)30입, count=1, size=박스, price=50500), ExcelData(clientName=계성고, itemName=냉동)로만)600닭강정 600*50, count=1, size=박스, price=17000), ExcelData(clientName=계성고, itemName=냉장)한성)매콤후랑크 800*80G*50(인상), count=1, size=박스, price=19500), ExcelData(clientName=계성고, itemName=비)톡젤리(청포도) 8입(통), count=1, size=통, price=0), ExcelData(clientName=계성고, itemName=비)톡젤리(딸기) 8입(통), count=1, size=통, price=0), ExcelData(clientName=계성고, itemName=비)톡젤리(믹스) 8입(통), count=1, size=통, price=0), ExcelData(clientName=계성고, itemName=비)톡젤리(복숭아) 8입(통), count=1, size=통, price=0), ExcelData(clientName=계성고, itemName=오뚜기)김치면(대) 12입, count=1, size=박스, price=7500), ExcelData(clientName=계성고, itemName=오뚜기)참깨컵(대)12입 1200*12, count=2, size=박스, price=8500), ExcelData(clientName=계성고, itemName=오뚜기)라면볶이(대) 12입, count=1, size=박스, price=7800), ExcelData(clientName=계성고, itemName=오뚜기)스파게티(대) 12입, count=1, size=박스, price=7500), ExcelData(clientName=계성고, itemName=오뚜기)짜장볶이(대) 12입 1000*12, count=1, size=박스, price=7800), ExcelData(clientName=계성고, itemName=비)500갈릭새우칩 500*30, count=1, size=박스, price=8400), ExcelData(clientName=계성고, itemName=비)500쌀떡볶이 500*30, count=1, size=박스, price=8400), ExcelData(clientName=계성고, itemName=비)200츄파춥스(봉지) 120입, count=1, size=봉, price=0), ExcelData(clientName=계성고, itemName=해태제과)허니버터칩 1500*16, count=1, size=박스, price=13200), ExcelData(clientName=계성고, itemName=롯데제과)꼬깔콘(매콤달콤) 1500*20입, count=1, size=박스, price=16500), ExcelData(clientName=계성고, itemName=매)피크닉(사과) 200ML*24, count=5, size=박스, price=5500), ExcelData(clientName=계성고, itemName=음료)레몬녹차 350ML*20, count=2, size=박스, price=12000), ExcelData(clientName=계성고, itemName=푸르밀)복숭아티(빵) 340ML*24, count=1, size=박스, price=10000), ExcelData(clientName=계성고, itemName=푸르밀)복숭아티(캔) 240ML*30, count=1, size=박스, price=8000), ExcelData(clientName=계성고, itemName=롯데음료)레쓰비(소) 150ML*30, count=5, size=박스, price=8000), ExcelData(clientName=이호수, itemName=롯데빵)갈릭소보로(박스) 16입, count=3, size=박스, price=10000), ExcelData(clientName=이호수, itemName=롯데빵)고소한땅콩샌드 20입, count=4, size=박스, price=12500), ExcelData(clientName=이호수, itemName=롯데빵)달나라(박스) 20입, count=1, size=박스, price=12500), ExcelData(clientName=이호수, itemName=롯데빵)달콤한땅콩소보로(박스) 20입, count=3, size=박스, price=12500), ExcelData(clientName=이호수, itemName=롯데빵)롱패스츄리(박스) 20입, count=3, size=박스, price=12500), ExcelData(clientName=이호수, itemName=롯데빵)모카케익(박스) 24입, count=2, size=박스, price=15000), ExcelData(clientName=이호수, itemName=롯데빵)빅단팥빵(박스) 20입, count=1, size=박스, price=12500), ExcelData(clientName=이호수, itemName=롯데빵)스위트딸기케익(박스) 24입, count=1, size=박스, price=15000), ExcelData(clientName=이호수, itemName=롯데빵)스위트패스츄리(박스) 20입, count=2, size=박스, price=12500), ExcelData(clientName=이호수, itemName=롯데빵)아몬드케익(박스) 20입, count=1, size=박스, price=12500), ExcelData(clientName=이호수, itemName=롯데빵)옥수수통단팥빵(박스) 20입, count=1, size=박스, price=12500), ExcelData(clientName=이호수, itemName=롯데빵)치즈케익(박스) 24입, count=1, size=박스, price=15000), ExcelData(clientName=이호수, itemName=롯데빵)클래식카스테라 24입, count=1, size=박스, price=15000), ExcelData(clientName=이호수, itemName=롯데빵)팥크림빵(박스) 24입, count=1, size=박스, price=15000), ExcelData(clientName=이호수, itemName=롯데빵)후르츠케익복숭아(박스) 24입, count=2, size=박스, price=15000), ExcelData(clientName=이호수, itemName=롯데빵)후르츠케익파인애플(박스) 24입, count=1, size=박스, price=15000), ExcelData(clientName=삼강-일진, itemName=빙그레)흰우유(박스)200ML*40입, count=12, size=박스, price=14400), ExcelData(clientName=삼강-일진, itemName=빙그레)바나나우유(박스) 240ML*32입, count=3, size=박스, price=23400), ExcelData(clientName=구미-오상고, itemName=삼립)미스터쉐프치즈(박스) 16입, count=1, size=박스, price=12800), ExcelData(clientName=구미-오상고, itemName=냉장)한성)매콤후랑크 800*80G*50(인상), count=2, size=박스, price=19500), ExcelData(clientName=구미-오상고, itemName=냉장)사조)남부불고기맛후랑크 70G*30, count=1, size=박스, price=13500), ExcelData(clientName=구미-오상고, itemName=파인)망사계란 3입*10줄, count=3, size=판, price=9000), ExcelData(clientName=구미-오상고, itemName=비)500캔디요미-복숭아(통) 35G*24, count=2, size=통, price=7200), ExcelData(clientName=구미-오상고, itemName=크라운제과)마이쮸(복숭아)(통) 800*15, count=2, size=통, price=6600), ExcelData(clientName=구미-오상고, itemName=비)500매콤한포테이토칩 40입, count=1, size=박스, price=10000), ExcelData(clientName=구미-오상고, itemName=비)500퍼지바(초코)(박스) 12*8, count=2, size=박스, price=26880), ExcelData(clientName=구미-오상고, itemName=비)500갈릭새우칩 500*30, count=1, size=박스, price=9000), ExcelData(clientName=구미-오상고, itemName=비)500감자알칩 500*40, count=1, size=박스, price=12000), ExcelData(clientName=구미-오상고, itemName=비)700브이콘(인상)*40, count=1, size=박스, price=16500), ExcelData(clientName=구미-오상고, itemName=비)1000바베큐맛스낵(인상) 20입, count=1, size=박스, price=12000), ExcelData(clientName=구미-오상고, itemName=비)새콤짱(포도맛)(박스) 600*96, count=1, size=박스, price=26880), ExcelData(clientName=구미-오상고, itemName=롯데제과)칸쵸 1000*32입, count=1, size=박스, price=16000), ExcelData(clientName=구미-오상고, itemName=롯데제과)ABC초코쿠키(쿠키) 1000*32, count=1, size=박스, price=16000), ExcelData(clientName=구미-오상고, itemName=매)허쉬드링크초콜릿 235ML*24(인상), count=2, size=박스, price=17000), ExcelData(clientName=구미-오상고, itemName=매)피크닉(사과) 200ML*24, count=3, size=박스, price=5600), ExcelData(clientName=구미-오상고, itemName=매)피크닉(청포도) 200ML*24, count=4, size=박스, price=5600), ExcelData(clientName=구미-오상고, itemName=코카)파워에이드(중)-인상 600ML*20, count=1, size=박스, price=22000), ExcelData(clientName=구미-오상고, itemName=음료)복숭아녹차 350ML*20, count=2, size=박스, price=12000), ExcelData(clientName=구미-오상고, itemName=롯데음료)레쓰비(소) 150ML*30, count=2, size=박스, price=8000), ExcelData(clientName=구미-오상고, itemName=음료)브이톡(블루레몬에이드) 350ML*24입, count=3, size=박스, price=13000), ExcelData(clientName=구미-오상고, itemName=음료)엔톡(청포도) 350ML*24입, count=2, size=박스, price=13000), ExcelData(clientName=구미-오상고, itemName=음료)모구모구(리취) 24입, count=2, size=박스, price=18000), ExcelData(clientName=구미-오상고, itemName=음료)모구모구(사과) 24입, count=2, size=박스, price=18000), ExcelData(clientName=구미-오상고, itemName=음료)모구모구(피치) 24입, count=2, size=박스, price=18000), ExcelData(clientName=구미-오상고, itemName=음료)모구모구(포도) 24입, count=2, size=박스, price=18000), ExcelData(clientName=구미-오상고, itemName=음료)모구모구(요구르트) 24입, count=2, size=박스, price=18000), ExcelData(clientName=2)경산고, itemName=대신)파)냉동)내가맛스타(박스) 30입, count=1, size=박스, price=22500), ExcelData(clientName=2)경산고, itemName=대신)파)냉동)치즈브래드(박스) 30입, count=1, size=박스, price=22500), ExcelData(clientName=2)경산고, itemName=대신)파)냉동)에이스팡(박스) 30입, count=1, size=박스, price=0), ExcelData(clientName=2)경산고, itemName=대신)파)냉동)직화불고기버거(박스) 30입, count=1, size=박스, price=0), ExcelData(clientName=2)경산고, itemName=대신)한맥)냉동)마시따 30입, count=1, size=박스, price=25000), ExcelData(clientName=2)경산고, itemName=대신)한맥)냉동)1200냉동불벅(박스) 30입, count=1, size=박스, price=25000), ExcelData(clientName=2)경산고, itemName=냉동)포켓치킨(데리야끼)30입, count=1, size=박스, price=50500), ExcelData(clientName=2)경산고, itemName=냉동)산동만두(매운맛)30입 30P, count=1, size=박스, price=26000), ExcelData(clientName=2)경산고, itemName=냉장)한성)매콤후랑크 800*80G*50(인상), count=1, size=박스, price=19500), ExcelData(clientName=2)경산고, itemName=냉장)사조)남부불고기맛후랑크 70G*30, count=1, size=박스, price=13000), ExcelData(clientName=2)경산고, itemName=비)1000바베큐맛스낵 100G*20입, count=1, size=박스, price=11400), ExcelData(clientName=영광, itemName=롯데빵)달콤한땅콩소보로(박스) 20입, count=1, size=박스, price=13200), ExcelData(clientName=영광, itemName=롯데빵)로투스연유크림빵(박스) 21입, count=1, size=박스, price=13860), ExcelData(clientName=영광, itemName=롯데빵)꼬마꿀호떡(박스) 16입, count=1, size=박스, price=10560), ExcelData(clientName=영광, itemName=삼립)빅패스츄리(박스) 14입, count=1, size=박스, price=10360), ExcelData(clientName=영광, itemName=삼립)미니치즈후레쉬팡(박스) 13입, count=1, size=박스, price=9620), ExcelData(clientName=영광, itemName=삼립)스트로베리데니쉬(박스) 21입, count=1, size=박스, price=15540), ExcelData(clientName=영광, itemName=삼립)옥수수이야기(박스) 22입, count=1, size=박스, price=16280), ExcelData(clientName=영광, itemName=삼립)핫더블소세지(박스) 27입, count=1, size=박스, price=19980), ExcelData(clientName=영광, itemName=삼립)허쉬초코롤(박스) 24입, count=1, size=박스, price=17760), ExcelData(clientName=영광, itemName=삼립)카스타드소보루(박스) 20입, count=1, size=박스, price=14800), ExcelData(clientName=영광, itemName=삼립)미스터쉐프치즈(박스) 16입, count=1, size=박스, price=11840), ExcelData(clientName=영광, itemName=대신)파)냉동)치즈브래드(박스) 30입, count=1, size=박스, price=22200), ExcelData(clientName=영광, itemName=대신)한맥)냉동)마시따 30입, count=1, size=박스, price=23700), ExcelData(clientName=영광, itemName=냉장)한성)매콤후랑크 800*80G*50(인상), count=2, size=박스, price=18500), ExcelData(clientName=영광, itemName=냉장)사조)남부불고기맛후랑크 70G*30, count=2, size=박스, price=12600), ExcelData(clientName=영광, itemName=오뚜기)진짬뽕컵(대) 12입 1500*12, count=2, size=박스, price=12500), ExcelData(clientName=영광, itemName=삼양)불닭볶음면큰컵(인상) 16입, count=2, size=박스, price=14500), ExcelData(clientName=영광, itemName=오뚜기)치즈볶이(대)12입 1200*12, count=2, size=박스, price=9300), ExcelData(clientName=영광, itemName=팔도)왕뚜껑(오리) 1050*18, count=2, size=박스, price=14400), ExcelData(clientName=영광, itemName=오뚜기)볶음진짬뽕컵(대) 12입, count=5, size=박스, price=12200), ExcelData(clientName=영광, itemName=빙그레1)멸균초코레떼(박스) 1000*24, count=2, size=박스, price=9500), ExcelData(clientName=영광, itemName=해태음료)자몽소다(빵) 355ML*24, count=5, size=박스, price=11000), ExcelData(clientName=2)덕원서점, itemName=빙그레)바나나우유(낱개) 1300*1, count=15, size=낱개, price=840), ExcelData(clientName=2)덕원서점, itemName=빙그레)초코타임우유(낱개) 500ML*1입, count=10, size=낱개, price=600), ExcelData(clientName=2)덕원서점, itemName=빙그레)흰우유(낱개) 700*1, count=10, size=낱개, price=420), ExcelData(clientName=2)덕원서점, itemName=롯데,삼립)섞어빵 1000*1, count=10, size=낱개, price=850), ExcelData(clientName=2)덕원서점, itemName=비)300본오본(밀크)(은색)(통) 300*30, count=1, size=통, price=4800), ExcelData(clientName=2)덕원서점, itemName=비)300본오본(초코)(갈색)(통) 30입, count=1, size=통, price=4800), ExcelData(clientName=2)덕원서점, itemName=크라운제과)새콤달콤(포도)(통) 500*15, count=2, size=통, price=4500), ExcelData(clientName=2)덕원서점, itemName=크라운제과)새콤달콤(딸기)(통) 500*15, count=2, size=통, price=4500), ExcelData(clientName=2)덕원서점, itemName=롯데제과)크런키볼 2500*6 (통), count=1, size=통, price=0), ExcelData(clientName=2)덕원서점, itemName=비)500쌀떡볶이 500*30, count=1, size=박스, price=9000), ExcelData(clientName=2)덕원서점, itemName=비)1000정성쫀디기 20입, count=1, size=박스, price=12000), ExcelData(clientName=2)덕원서점, itemName=해태제과)홈런볼(초코) 1500*30, count=1, size=박스, price=27000), ExcelData(clientName=2)덕원서점, itemName=크라운제과)화이트하임 1400*18, count=1, size=박스, price=15120), ExcelData(clientName=2)덕원서점, itemName=롯데제과)ABC초코쿠키 1000*32, count=1, size=박스, price=19200), ExcelData(clientName=2)덕원서점, itemName=농심)1000쫄병스낵(바베큐맛) 30입, count=1, size=박스, price=21900), ExcelData(clientName=2)덕원서점, itemName=매)피크닉(사과) 200ML*24, count=2, size=박스, price=5640), ExcelData(clientName=2)덕원서점, itemName=매)바리스타(카라멜딥프레소) 250ML*10, count=1, size=박스, price=13600), ExcelData(clientName=2)덕원서점, itemName=동아)나랑드(P/T) 500ML*20, count=1, size=박스, price=12000), ExcelData(clientName=2)덕원서점, itemName=동아)포카리(중) 500ML*20, count=2, size=박스, price=19800), ExcelData(clientName=2)사동고, itemName=대신)파)1000맛스타(박스) 40입, count=1, size=박스, price=28000), ExcelData(clientName=2)사동고, itemName=빙그레)초코타임우유(낱개) 500ML*1입, count=15, size=낱개, price=0), ExcelData(clientName=장원상회, itemName=빙그레)바나나우유(박스) 240ML*32입, count=3, size=박스, price=24000), ExcelData(clientName=장원상회, itemName=빙그레)딸기단지우유(박스) 240ML*32입, count=1, size=박스, price=26000), ExcelData(clientName=장원상회, itemName=대신)파)1000시카고피자(박스) 30입, count=2, size=박스, price=22000), ExcelData(clientName=장원상회, itemName=대신)파)냉동)치즈브래드(박스) 30입, count=1, size=박스, price=22000), ExcelData(clientName=장원상회, itemName=대신)한맥)냉동)마시따 30입, count=2, size=박스, price=23700), ExcelData(clientName=장원상회, itemName=대신)파)냉동)내가맛스타(박스) 30입, count=2, size=박스, price=22000), ExcelData(clientName=장원상회, itemName=빙그레)카톤(CS), count=1, size=박스, price=1000), ExcelData(clientName=혜성(장영덕, itemName=롯데빵)롱패스츄리(박스) 20입, count=6, size=박스, price=13900), ExcelData(clientName=혜성(장영덕, itemName=롯데빵)스노우붓세딸기(박스) 24입, count=5, size=박스, price=16680), ExcelData(clientName=혜성(장영덕, itemName=빙그레)흰우유(박스)200ML*40입, count=8, size=박스, price=14800), ExcelData(clientName=삼우자판기, itemName=대신)파)1000맛스타(박스) 40입, count=1, size=박스, price=28000), ExcelData(clientName=삼우자판기, itemName=대신)파)1000불치즈버거(박스) 40입, count=1, size=박스, price=28000), ExcelData(clientName=삼우자판기, itemName=대신)파)1000치즈브래드(박스) 40입, count=1, size=박스, price=28000), ExcelData(clientName=삼우자판기, itemName=대신)파)1000핫바베큐(박스) 40입, count=1, size=박스, price=28000), ExcelData(clientName=삼우자판기, itemName=대신)파)1000핫치킨바베큐(박스) 40입, count=1, size=박스, price=28000), ExcelData(clientName=삼우자판기, itemName=대신,햄버거)섞어버거 1000*1, count=-80, size=낱개, price=700), ExcelData(clientName=신재원, itemName=빙그레)바나나우유(낱개) 1300*1, count=20, size=낱개, price=750), ExcelData(clientName=신재원, itemName=빙그레)흰우유(낱개) 700*1, count=20, size=낱개, price=400), ExcelData(clientName=윤권유통, itemName=입금 계좌, count=0, size=blank, price=0), ExcelData(clientName=윤권유통, itemName=매출합계: , count=848, size=blank, price=420000), ExcelData(clientName=윤권유통, itemName=정상 반품: , count=-80, size=blank, price=0), ExcelData(clientName=윤권유통, itemName=입금 계좌: , count=0, size=blank, price=0)])

    }
    @Test
    void 오더생성테스트() {
//        orderService
    }
}