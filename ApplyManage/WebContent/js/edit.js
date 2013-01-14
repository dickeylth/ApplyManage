$(document).ready(function(){
	//初始化工作
	(function(){
		$('.checkboxLabel').each(function(){
			$(this).after($('<br/>'));
		});
		$('.text_url').each(function(){
			$a = $('<a>点击查看</a>').attr('href', $(this).val()).attr('target', 'view');
			$a.click(function(){
				$('#view').show();
				$('#close').show();
			});
			$(this).after($a).remove();
		});
		$('#close').click(function(){$('#view').hide();$(this).hide();});
		$('body').show();
	})();
	
});