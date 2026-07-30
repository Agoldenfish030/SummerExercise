# SummerExercise：基隆景點

## 系統功能

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


## 測試方式


## API範例


## 環境變數清單
* PORT：8080
* ATLAS_MONGODB：雲端資料庫mongoDB Atlas uri

## 實作（截圖）
![]

## 公開網址


（已知限制？）