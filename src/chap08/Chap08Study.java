package chap08;

public class Chap08Study {

}

/*
「バリデーション」とは、入力内容が要件を満たしているか、妥当性を確認する入力チェックのことである。
バリデーションは大きく分けると以下の2つに分かれる。
・単項目チェック
・相関項目チェック（相関チェック）
「単項目チェック」とは、入力項目1つ1つに対して、設定する入力チェック機能である。
使用方法はFormクラスなどのフィールドにアノテーションを付与する。
入力チェックのアノテーションは、Java EEが提供するアノテーション「Bean Validatin」や
Hibernateフレームワークが提供するアノテーション「hibernate Validator」がある。

○単項目チェックで利用される主なアノテーション
|アノテーション|機能概要                                                |
|@NotNull      |nullでないことを検証                                    |
|@NotEmpty     |文字列がnullまたは空文字("")でないか検証                |
|@Max          |指定した数値以下であることを検証                        |
|@Min          |指定した数値以上であることを検証                        |
|@Size         |文字列やCollectionが指定した範囲の大きさであることを検証|
|@AssertTrue   |trueであることを検証                                    |
|@AssertFalse  |falseであることを検証                                   |
|@Range        |指定された数値の範囲内であるか検証                      |
|@Email        |文字列がEmail形式か検証                                 |

「相関項目チェック」は複数のフィールドを混合してチェックを行う。
相関項目チェックを作成する方法には以下の2種類がある。
・「Bean Validation」を利用する方法
・Spring Frameworkが提供する「Validator」インターフェースを実装する方法

一般的にアプリケーションで表示するメッセージはプログラムとは別に管理する。
主な理由として値をテンプレートから分離し、値そのものをプロパティファイルで管理することで、
後々のメンテナンスが容易になるからである。
Spring Bootを使用した開発では、
バリデーションに対応するメッセージは「ValidationMessages.properties」、
それ以外のメッセージは「messages.properties」に記述する。
*/
