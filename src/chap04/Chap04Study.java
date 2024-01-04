package chap04;

public class Chap04Study {
	//idカラムに対応
	private Integer id;
	//nameカラムに対応
	private String name;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
/*
「データベース」とはデータを格納するための入れ物である。データの集まりに何らかのルールを持たせ、データを整理し保持する。
また、データを表形式で表し、複数の表が項目の値で関連付けされているデータベースを「リレーショナルデータベース（RDB）」
といい、テーブルとテーブルとの関連のことを「リレーションシップ」という。


○「PostgreSQL」専用の管理ソフトウェアである「pgAdmin4」のデータベース確認方法
1.pgAdmin4の起動
2.データベースの確認
PostgreSQLでは初期化時に「postgres」という名称のデータベースが作成される。


データベースの中で実際にルールを持たせたデータが格納される入れ物を「テーブル」という。
テーブルはデータを関連する項目で2次元の表形式に整理し格納する。
テーブルで横の行を「レコード」といい、1レコードが1件のデータに対応する。
また、縦の列を「カラム」といい、1列はデータの各要素に対応する。


テーブルに存在するデータを不整合な状態にさせないためのルールを「制約」という。
○制約の一例
|制約       |概要                                                                           |
|NOT NULL   |NULL（空）値の入力を許さない                                                   |
|UNIQUE     |重複する値を許さない                                                           |
|CHECK      |指定した条件を満たす値の入力しか許さない                                       |
|PRIMARY KEY|テーブル内のレコードを識別する「主キー」を設定、主キーとはNOT NULL かつ UNIQUE |
|FOREIGN KEY|関連したテーブル間を結ぶために設定、「外部キー」ともよばれる                   |
|DEFAULT    |カラムにデフォルトの値を設定できる                                             |


○テーブルの作成
1.「Servers」→「PostgreSQL12」→「Databases」→「postgres」→「Schemas」
2.→「Tables」を右クリックし「Create」→「Table」をクリックすると「Create-Table」画面が表示される
3.「General」タブでの[Name]はテーブルの名前になる
4.「Columns」タブでの[+]はカラムの作成、[Name]はカラム名になる


データベースを操作するための言葉を「Structured Query Language(SQL)：ストラクチャード・クエリ・ランゲージ」といい、
SQLはANSI（American National Standard Institute）やISO（国際標準化機構）で規格が標準化されているため、
どのデータベースでもほぼ同じように操作可能である。
さらに、SQLで永続的なデータを取り扱う4つの基本機能を「CRUD」といい、CRUDは、「作成（Create）」、「読み出し（Read）」、
「更新（Update）」、「削除（Delete）」の頭文字をつなげた言葉である。
○SQLでのCRUD
|CRUD          |命令  |概要            |
|作成(Create)  |INSERT|データを登録する|
|読み出し(Read)|SELECT|データを参照する|
|更新(Update)  |UPDATE|データを更新する|
|削除(Delete)  |DELETE|データを削除する|
○PostgreSQLでのCRUD構文
|CRUD          |構文                                                                                                                 |
|作成(Create)  |INSERT INTO テーブル名(列名, 列名, ...) VALUES(値, 値, ...);                                                         |
|読み出し(Read)|SELECT 列名 FROM テーブル名;                                                                                         |
|更新(Update)  |UPDATE テーブル名 SET 列名 = 値 WHERE 更新する行を特定する条件; (WHEREで条件を指定しない場合、すべてのレコードが対象)|
|削除(Delete)  |DELETE FROM テーブル名 WHERE 削除する行を特定する条件; (WHEREで条件を指定しない場合、すべてのレコードが対象)         |


○pgAdmin4でテーブルにデータを投入
1.INSERTの実行
「Tables」→「member」を選択し、メニューバーにて「Tools」→「Query Tool」を選択すると「Query Editor」画面が表示される。
SQLで入力後、「➤」ボタンをクリックする。
例）
INSERT INTO member(name) VALUES('太郎');
INSERT INTO member(name) VALUES('ジロウ');
2.SELECTの実行
「Tables」→「member」を選択し、メニューバーにて「Tools」→「Query Tool」を選択すると「Query Editor」画面が表示される。
SQLで入力後、「➤」ボタンをクリックする。
例）
SELECT id, name FROM member;
3.UPDATEの実行
「Tables」→「member」を選択し、メニューバーにて「Tools」→「Query Tool」を選択すると「Query Editor」画面が表示される。
SQLで入力後、「➤」ボタンをクリックする。
例）
UPDATE member SET name='JIROU' WHERE id=2;
4.DELETEの実行
「Tables」→「member」を選択し、メニューバーにて「Tools」→「Query Tool」を選択すると「Query Editor」画面が表示される。
SQLで入力後、「➤」ボタンをクリックする。
例）
DELETE FROM member WHERE id=2;


データベースのテーブルの1行に対応するクラス（データの入れ物となるクラス）を「Entity（エンティティ）」という。
また、データベースへのデータベース操作を行うクラスを「Repository（リポジトリ）」という。
リポジトリを作成する場合は、必ずインターフェースを定義したうえで実装する。
エンティティには使用方法の特徴が3つある。
1.クラス名
対応するデータベースのテーブル名と同じにすることが多い
2.データベースへ値の引き渡し
データベースへ値を登録・更新する場合に、エンティティに値を入れて引き渡す
3.データベースから値の取得
データベースから値を取得した場合に、値をエンティティに入れて保持しておく。


Javaプログラム開発では、データベースとのアクセス処理には「O/Rマッパー」というフレームワークを使用する開発が多い。
O/Rマッパーとは、アプリケーションで扱う「O:オブジェクト」と「R:リレーショナルデータベース」とのデータをマッピングするもので、
詳細に説明すると、あらかじめ設定された「O:オブジェクト」と「R:リレーショナルデータベース」との対応関係の情報に基づき、
インスタンスのデータを対応するテーブルへ書き出したり、データベースから値を読み込んでインスタンスに代入したり
といった操作を自動的に行ってくれる。
O/Rマッパーの1つに「Spring Date JDBC」というものがあり、特徴として、Spring Dateが提供するCrudRepositoryを継承することで、
自動的にCRUDをサポートするメソッドを使用できる。
*/
