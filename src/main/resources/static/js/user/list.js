'use strict';

var userData = null;
var table = null;

jQuery(function($){

	//初期化
	createDataTables();

	/**検索 */
	$('#btn-search').click(function (event){
		search();
	})
});

function search(){

	var formData = $('#user-search-form').serializeArray();

	$.ajax({
		type:"GET",
		cache: false,
		url: '/user/get/list',
		data: formData,
		dataType: 'json',
		contentType: 'application/json; charset=UTF-8',
		timeout: 5000,
	}).done(function(data){
		userData = data;
		createDataTables();
	}).fail(function(jqXHR,textStatus,errorThrown){
		alert('検索処理に失敗しました');
	}).always(function(){
	})
}

/**データテーブルの作成 */
function createDataTables() {
	if(table !== null){
		table.destroy();
	}
	table = $('#user-list-table').DataTable({
		language: {
			url: '/webjars/datatables-plugins/i18n/Japanese.json'
		},
		data: userData,
		columns: [
			{data: 'userId'},
			{data: 'userName'},
			{
				data: 'birthday',
			 	render: function (data,type,row){
					var date = new Date(data);
					var year = date.getFullYear();
					var month = date.getMonth()+1;
					var date = date.getDate();
					return year + '/' + month + '/' + date;
				}
			},
			{data: 'age'},
			{
				data: 'gender',
				render: function (data,type,row){
					var gender = '';
					if(data===1){
						gender = '男性';
					}else{
						gender = '女性';
					}
					return gender;
				}
			},
			{
				data: 'userId',
				render: function(data,type,row){
					var url = '<a href="/user/detail/' + data + '">詳細</a>'
					return url;
				}
			},
		]
	})
}