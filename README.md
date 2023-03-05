# 2DCellularAutomata
2次元セルオートマトンを表示する。

y=255の平面をt=0として、その初期値に基づきy<255の平面を求める
## コマンド
### 範囲指定
(x座標とz座標しか使わない)y座標は全領域使う
- /position1 - 範囲指定pos1
- /position2 - 範囲指定pos2 
### 初期化
設定の初期化
- /initialize
### ランダマイズ
y=255での平面をランダム化する
- /randomize
### 生成
- /timeevolution <neighbor_rule> <rule_num>

#### パラメータ
|  neighbor_rule  |  意味  | rule_num 範囲 |
| :---: | :---: | :---: |
| 0 |周囲4マスの値の総和と自身の値をもとに指定 |0-1024|
| 1 |周囲8マスの値の総和と自身の値をもとに指定 |0-262144|
| 2 |周囲4マスと自身の値の総和をもとに指定|0-64|
| 3 |周囲8マスと自身の値の総和をもとに指定|0-1024|

### 実例
/timeevolution 0 10
![2023-03-05_21 10 27](https://user-images.githubusercontent.com/115648249/222961890-cd7b0b15-839e-4d0d-a133-4c24eb477879.png)

/timeevolution 3 98
![2023-03-05_21 20 34](https://user-images.githubusercontent.com/115648249/222961899-d993d235-5923-4fe8-9fce-6802d653f060.png)
