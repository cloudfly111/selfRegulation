# 自我狀態管理 App
> 目的：主要是由 App 彙整生活中工作、健康指標與壓力相關狀態以管理自身身體狀態
- #### App 適用 Android 版本：Android 9 以上

### build 環境：
- IDE：Android Studio Chipmunk | 2021.2.1 Patch 1
- Kotlin version：1.7.10
### 架構：MVVM
主要拆成：
- Model：資料
- View：畫面元件
- ViewModel：寫商業邏輯(ex:點按鈕要顯示什麼內容)
### 預計使用的套件/資料庫
- 本地資料庫：Room
- 遠端資料庫：FireBase RealtimeDatabase 
- API 串接：Retrofit
- 異步處理：Coroutines , RxJava
## 預計頁面功能流程
![App架構規劃_20231223](https://github.com/cloudfly111/selfRegulation/assets/37395516/389ef5e9-56c2-43e5-9e40-15b150b8708e)

## 目前進度 demo 影片

- 已使用的套件：
  - 異步處理：Coroutines , RxJava
    - Coroutines：首頁 ListView 異步更新
    - RxJava：歡迎頁倒數計時器

https://github.com/cloudfly111/selfRegulation/assets/37395516/28675b8e-1a1c-4863-adc1-f8fab2d7aff1



