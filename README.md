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
3. 此步驟部署專案的方法為Railway + github。將zip檔的內容上傳到github後，透過連結github以部署到Railway。
4. 設定Variables（環境變數），並在Setting欄位更改：
   * Source的Root Directory改成/backend
   * Build的Builder改為Dockerfile，並設定Dockerfile路徑
## 測試方式

## API範例

## 環境變數清單
* PORT：8080或自訂
* ATLAS_MONGODB：雲端資料庫mongoDB Atlas uri
## 實作（截圖）
![webite](/docs/website.png)
![websiteRWD](/docs/websiteRWD.png)
## 公開網址
https://backend-mavenmongodb-production.up.railway.app/

## 已知限制
* 爬蟲目標網站更新內容時，雲端資料庫並不會即時更新，需要在Railway。