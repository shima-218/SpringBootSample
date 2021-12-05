'use strict';

jQuery(function($){

	/**登録 */
	$('#btn-signup').click(function (event){
		signupUser();
	})
});

function signupUser(){

	//バリデーション結果をクリア
	removeValidResult();

	var formData = $('#signup-form').serializeArray();

	$.ajax({
		type:"POST",
		cache: false,
		url: '/user/signup/rest',
		data: formData,
		dataType: 'json',
	}).done(function(data){
		if(data.result === 90){
			$.each(data.errors,function(key,value) {
				reflectValidResult(key,value)
			});
		}else if (data.result === 0){
			alert('ユーザーを登録しました');
			window.location.href="/login";
		}
	}).fail(function(jqXHR,textStatus,errorThrown){
		alert('ユーザー登録に失敗しました');
	}).always(function(){
	})
}

/**バリデーション結果のクリア */
function removeValidResult(){
	$('.is-invalid').removeClass('is-invalid');
	$('.invalid-feedback').remove();
	$('.text-danger').remove()
}

/**バリデーション結果の反映 */
function reflectValidResult(key,value){
	if(key === 'gender'){
		//CSS適用
		$('input[name=' + key + ']').addClass('is-invalid');
		//エラーメッセージ追加
		$('input[name=' + key + ']').parent().parent()
		.append('<div class="text-danger">' + value + '</div>')
	}else {
		//CSS適用
		$('input[id=' + key + ']').addClass('is-invalid');
		//エラーメッセージ追加
		$('input[id=' + key + ']')
		.after('<div class="invalid-feedback">' + value + '</div>')
	}

}