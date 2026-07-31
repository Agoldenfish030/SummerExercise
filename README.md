# SummerExercise：基隆景點

## 系統功能
此專案架構為前端與後端並存的Monorepo（單一儲存庫），是一個介面簡單的基隆景點導覽網頁，可按下七個基隆地區的按鈕快速瞭解基隆景點。
* **自動爬蟲並於mongoDB預存資料：** 
透過爬取[TravelKing旅遊王的基隆景點地圖](https://www.travelking.com.tw/tourguide/taiwan/keelungcity/)，將所需的資訊預存於指定的mongoDB Atlas，每次開啟網頁便不需等待冗長的爬蟲時間。
* **一鍵瞭解基隆地區景點：**
點擊地區按鈕，下方將會顯示該區的特色景點卡牌，可再點擊卡牌下方按鈕瞭解詳細資訊。
## 架構概覽
```
SummerExercise/
├──backend/
│  ├──.mvn/
│  │  └──wrapper
│  │     └──maven-wrapper.properties
│  ├──src/
│  │  ├──main/
│  │  │  ├──java/
│  │  │  │  └──com/
│  │  │  │     └──exercise/
│  │  │  │        └──backend/
│  │  │  │           ├──controller/
│  │  │  │           │  ├──KeelungSightsRepository.java
│  │  │  │           │  ├──KeelungSightsService.java
│  │  │  │           │  └──SightController.java
│  │  │  │           ├──crawler/
│  │  │  │           │  ├──KeelungSightsCrawler.java
│  │  │  │           │  └──Sight.java
│  │  │  │           ├──runner/
│  │  │  │           │  └──SightsApplicationRunner.java
│  │  │  │           ├──zoneName/
│  │  │  │           │  └──ZoneConstant.java
│  │  │  │           └──Application.java
│  │  │  └──resources/
│  │  │     ├──static/
│  │  │     │  ├──assets/
│  │  │     │  │  ├──index-7NA-iUKR.css
│  │  │     │  │  └──index-P924cIHq.js
│  │  │     │  ├──favicon.svg
│  │  │     │  └──index.html
│  │  │     └──application.properties
│  │  └──test/
│  │     └──java/
│  │        └──com/
│  │           └──exercise/
│  │              ├──backend/
│  │              │  └──crawler/
│  │              │     └──TestKeelungSightsCrawler.java
│  │              └──ApplicationTests.java
│  ├──.gitattributes
│  ├──.gitignore
│  ├──Dockerfile
│  ├──mvnw
│  ├──mvnw.cmd
│  └──pom.xml
└──frontend/
   ├──public/
   │  └──favicon.svg
   ├──src/
   │  ├──components
   │  │  └──RequestSight
   │  │     ├──SightButton.jsx
   │  │     └──SightCards.jsx
   │  ├──App.css
   │  ├──App.jsx
   │  └──main.jsx
   ├──.gitignore
   ├──eslint.config.js
   ├──index.html
   ├──package.json
   ├──package-lock.json
   └──vite.config.js
```
## 版本使用
* JDK：17
* Maven：4.0.0
* node：11.6.1
## 安裝與執行步驟
1. 下載本專案的github zip檔。
2. 先架構後端。創建一個mongoDB Atlas的cluster。
3. 此步驟部署專案的方法為railway + github。將zip檔的內容上傳到github後，透過連結github以部署到railway。
4. 設定Variables（環境變數），並在Setting欄位更改：
   * Source的Root Directory改成/backend
   * Build的Builder改為Dockerfile，並設定Dockerfile路徑：/backend/Dockerfile
## 測試方式
* **爬蟲測試：**
可運行/backend/src/test/java/com/exercise/backend/crawler/TestKeelungSightsCrawler.java進行爬蟲測試。
* **API測試：**
使用Postman，在本地或railway部署的網頁後面加上'/api/sights/'（需去掉''）+地區英文（qidu/zhongshan/zhongzheng/renai/anle/xinyi/nuannuan）進行GET請求。
## API範例
* **爬蟲API（以安樂區為例）：**
```JSON
[
    {
        "address": "基隆市安樂區武訓街51號",
        "category": "觀光工廠",
        "description": "成立已逾35個年頭的毅太企業，擁有40 多項淋浴門專利，為目前國內最大淋浴拉門製造商。近年更代理日本、德國等廚衛大廠精品，除了提供優質的衛浴用品，近年更嘗試多元化發展，成立亞洲唯一的衛浴文化館，展示東西方衛浴文化的演進，同時讓您體驗最新的衛浴精品，2010 年與在地基隆歷史文化結合，成立「1560 雞籠故事館」，是全國首座結合科技、環保、文化、養生之衛浴觀光工廠！",
        "photoURL": "https://www.travelking.com.tw/photo.travelking.com.tw/scenery/2CBFC310-05B5-47DF-BEFE-BD606BF7E008_d.jpg",
        "sightName": "一太e衛浴觀光工廠",
        "zone": "安樂區"
    },
    {
        "address": "基隆市安樂區基金一路208巷19號",
        "category": "風景區",
        "description": "大武崙砲台位在大武崙山中、情人湖東方，砲台、古堡均是清道光初年中法戰爭所留下來的遺跡。大武崙砲台是扼守基隆港口西側的重要據點，在英法聯軍及中法戰爭時，清廷均曾派其駐防。特別是中法戰爭的時候，只有台灣的戰場，戰勝法軍。當日本要接收台灣時，還因為畏懼基隆的砲台，守備完備，因而特地繞道貢寮海邊登陸。如今所看到的大武崙砲台，多數為日治時期修建，規模不小迄今尚稱完整，已列國定古蹟。",
        "photoURL": "https://www.travelking.com.tw/photo.travelking.com.tw/scenery/4555FD29-1E57-40AE-B163-767727AD8631_d.jpg",
        "sightName": "大武崙砲台",
        "zone": "安樂區"
    },
    {
        "address": "基隆市安樂區麥金路",
        "category": "風景區",
        "description": "在基隆麥金公路及八德路附近，有一個新建的賞鳥區，位於基隆河及大武崙溪畔，這裡常可見水鳥白翎鷥等翱翔覓食，那就是水頭賞鳥區。白翎鷥因具有白色絲狀長飾羽而得名，是愛清潔的鳥類，為了保持全身白色羽毛的清潔，在胸、腹和胯部，有些則在背部等處有粉綿區，為其它許多鳥類所無之特殊羽毛。其羽毛先端經常分化成細粉狀，牠們常用其嘴，或腳趾，將此白粉塗於體羽，以避免被魚類的黏液或淤泥污染。白翎鷥為頸長，腳亦長的涉禽。通常在淺水處涉水覓魚蝦之外，亦好吃水邊之小蟹、水蛙等水生動物。分布於台灣之鷺科鳥類，１８種之中，只有小白鷺、黃頭鷺、夜鷺等三種有群集繁殖習性，而其群集營巢處，即所謂白翎鷥穴、白翎鷥洞，依勘奧學所言，為地靈厚實凝聚之奇穴，並非指洞穴。惟依生態上而言，其營巢之環境，大都在山腳或近海邊的樹林，選擇在濃密的樹林、竹林，能避風，不受任何人為干擾，附近有供給充份食物之溪流、泥灘、海灘或水稻田等。瞭解了白翎鷥，在賞鳥孔牆上，靜靜地欣賞其舞姿，真的很美！",
        "photoURL": "https://www.travelking.com.tw/photo.travelking.com.tw/scenery/753AE408-34AE-458E-96DF-FCD69F5C06A7_d.jpg",
        "sightName": "水頭賞鳥區",
        "zone": "安樂區"
    },
    {
        "address": "基隆市安樂區基金一路208巷",
        "category": "風景區",
        "description": "情人湖位於大武崙山山腰上的高地湖泊，景色清純樸實。自然形成的小湖，在珊瑚般分岔的山域，由幾條小斷頭溪匯集而成，就是情人湖，原名五義碑。湖面周圍，林木濃密，清靜幽雅，在林間的情人步道踏青，可享受天然的森林浴。環湖山地上，設有三座平台，可從不同角度遠眺景觀。湖旁亦可烤肉、露營。情人湖之北，有步道可登臨面海的山嶺，從稜線的步道上觀海，不僅可見到岸邊的澳底漁港，亦可遠眺基隆嶼及野柳海岬。聽說情人湖上的情人橋有某種魔力，有許多情人到此一遊，攜手走過情人湖，就會有好的婚姻。情人湖正東方的大武崙山，不僅為附近最高峰，也是值得憑弔的古蹟。山頂的砲台建於滿清道光年間，是中法戰爭留下的古蹟之一。山中古蹟之涵蓋面積很廣，及許多建築仍舊保存很多，老榕樹盤結糾纏其間，增添不少古意。在砲山山頂眺望，外木漁村、八斗子、萬里、基隆等景色盡入眼簾。",
        "photoURL": "https://www.travelking.com.tw/photo.travelking.com.tw/scenery/38815A18-6D62-4950-A6B2-05FA0D8138A4_d.jpg",
        "sightName": "情人湖",
        "zone": "安樂區"
    },
    {
        "address": "基隆市安樂區武嶺街",
        "category": "風景區",
        "description": "新山水庫位於基隆市武嶺街上，由於道路上無景點指標，旅遊書籍亦無相關介紹，使得這個水庫顯得格外幽靜美麗。水庫本身水面十分寬廣，比基隆市的情人湖大，只是目前尚無道路可環湖，只能從水庫旁道路及觀景亭上一窺其貌。",
        "photoURL": "https://www.travelking.com.tw/photo.travelking.com.tw/scenery/9F1F786B-4547-4B39-8F9B-4CBD11CA9243_d.jpg",
        "sightName": "新山水庫",
        "zone": "安樂區"
    },
    {
        "address": "基隆市安樂區鶯歌里八德路81號",
        "category": "歷史古蹟",
        "description": "獅球嶺隧道位於基隆市，又稱為劉銘傳隧道，全長約235公尺，為基隆市市定古蹟。獅球嶺隧道主要的工匠多係徵調兵工，另又聘請了數位英、德工程師為顧問，根據當時的文獻記載，因為地層土質複雜，北段為堅硬的岩石，南段為潮濕的軟土，開鑿極為困難；獅球嶺隧道雖然埋身在林蔭樹叢間，但卻是在台灣鐵路興築史上，有著其重要的意義。",
        "photoURL": "https://www.travelking.com.tw/photo.travelking.com.tw/scenery/FD625C8B-FBD9-480A-947F-87B34F6FDD30_d.jpg",
        "sightName": "獅球嶺隧道(劉銘傳隧道)",
        "zone": "安樂區"
    }
]
```
## 環境變數清單
* PORT：8080或自訂
* ATLAS_MONGODB：雲端資料庫mongoDB Atlas uri
## 實作（截圖）
![webite](/docs/website.png)
![websiteRWD](/docs/websiteRWD.png)
## 公開網址
https://backend-mavenmongodb-production.up.railway.app/

## 已知限制
* 爬蟲目標網站更新內容時，雲端資料庫並不會即時更新，需要在railway重新Deploy才可更新。
* 必須有github、mongoDB、railway帳號，若要進行爬蟲測試與API測試，則各自需要下載IDE和Postman。