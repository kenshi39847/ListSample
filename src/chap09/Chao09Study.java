package chap09;

public class Chao09Study {

}

/*
これまで学習した内容を使用し、Webアプリケーションを作成する。作成する内容は「○×クイズ」である。
○作成機能一覧
|No|機能      |説明                        |
|1 |登録機能  |クイズを登録する            |
|2 |更新機能  |登録したクイズを変更する    |
|3 |削除機能  |登録したクイズを削除        |
|4 |一覧表示  |登録したクイズを一覧表示する|
|5 |ゲーム機能|クイズを解く                |

○URL一覧
|No|役割                |HTTPメソッド|URL         |
|1 |一覧画面を表示する  |GET         |/quiz       |
|2 |登録処理を実行する  |POST        |/quiz/insert|
|3 |更新画面を表示する  |GET         |/quiz/{id}  |
|4 |更新処理を実行する  |POST        |/quiz/update|
|5 |削除処理を実行する  |POST        |/quiz/delete|
|6 |クイズ画面を表示する|GET         |/quiz/play  |
|7 |クイズを解く        |POST        |/quiz/check |

アプリケーションを作成する際はレイヤで分けることが推奨されている。
今回は以下の3レイヤで分割する。
○アプリケーション層
クライアントから受け取ったリクエストを制御し、ドメイン層を使ってアプリケーションを制御する
Controller、Form、View
○ドメイン層
Domain Objectに対するアプリケーションのサービス処理を実行する
Domain Object、Service、Repository
○インフラストラクチャ層
Domain Objectに対するCRUD操作を行い、データ永続化を担う
Repositorympl、O/R Mapper

○コンポーネント一覧
|No|層                    |コンポーネント|名称          |備考                     |
|1 |アプリケーション層    |Controller    |QuizController|制御の役割               |
|2 |アプリケーション層    |Form          |QuizForm      |画面のフォームを表現     |
|3 |アプリケーション層    |View          |-             |見た目                   |
|4 |ドメイン層            |Domain Object |Quiz          |Entityと同様             |
|5 |ドメイン層            |Service       |QuizService   |インターフェースで作成   |
|6 |ドメイン層            |Servicempl    |QuizServicempl|Serviceを実装            |
|7 |ドメイン層            |Repository    |QuizRepository|インターフェースで作成   |
|8 |インフラストラクチャ層|Repositorympl |-             |O/Rマッパーにより自動作成|
|9 |インフラストラクチャ層|O/R Mapper    |-             |Spring Data JDBCを使用   |
*/