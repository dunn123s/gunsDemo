layui.define(['jquery', 'laytpl', 'form', 'dict'], function (exports) {
	var $ = layui.jquery;
	var form = layui.form;
	var dict = layui.dict;
	// 级联关系：key-filterId, value=[]下级联动elem数组
	var CASCADE = {};
	var CASCADE_EVENT = {};
	
	function attr($el, attrName, defaultVal, trim) {
		var val = $el.attr(attrName);
		if(!val || val.trim() === "") {
			val = defaultVal;
		}
		if(val && (!trim || trim !== false)) {
			val = val.replace(/\s+/g, '');
		}
		return val;
	}
	
	// 初始化下拉框，并收集CASCADE
	var optpl = layui.laytpl('<option value="{{d.code}}">{{d.name}}</option>');
	function initSelect(select, first) {
		var $select = $(select);
		$select.empty();
		var dictType = attr($select, 'dict-type');
		var dictFilter = attr($select, 'dict-filter', null);
		var dictName = attr($select, 'dict-name', 'name');
		var dictValue = attr($select, 'dict-value', 'code');
		var dictParam = attr($select, 'dict-param', 'filter');
		var placeholder = attr($select, 'placeholder', '请选择');
		var dictData = attr($select, 'dict-data', '');
		$select.append(optpl.render({code: '', name: placeholder}))
		var data = [];
		var query = true; // 是否查询数据
		// 根据dict-type联动：主要用于一级数据字典的值等于二级数据字典的type
		var cascadeByDictType = (dictType && dictType.indexOf("elem:") == 0);
		if (cascadeByDictType === true){
			var filterId = dictType.substring(5);
			if(first === true) {
				if(CASCADE[filterId]) {
					CASCADE[filterId].push(select);
				}else {
					CASCADE[filterId] = [select];
				}
			}
			var $primary = $('[lay-filter='+filterId+']');
			dictType = $primary.val();
			if(!dictType || dictType == '') query = false;
		}
		// 根据dict-fitler联动
		var filter = {};// key-下拉框对应数据集的属性、value-过滤值
		var filterAttrs = dictParam.split(',');
		if(dictFilter && dictFilter.indexOf("elem:") == 0) {// 联动下拉框的上级下拉框选择符
//			if(cascadeByDictType === true) throw '已使用dict-type联动，不能再使用dict-filter联动';
			var filterIds = dictFilter.substring(5).split(',');
			for(var i in filterIds) {
				var filterId = filterIds[i];
				var filterAttr = filterAttrs[i];
				if(first === true) {
					if(CASCADE[filterId]) {
						CASCADE[filterId].push(select);
					}else {
						CASCADE[filterId] = [select];
					}
				}
				var $primary = $('[lay-filter='+filterId+']');
				var filterVal = $primary.val();
				if(filterVal && filterVal != "") {
					filter[filterAttr] = filterVal;
				}else {// 联动条件不满足，不走查询
					query = false;
				}
			}
		}else {
			if(dictFilter) {
				var filterVals = dictFilter.split(',');
				for(var i in filterAttrs) {
					var filterAttr = filterAttrs[i];
					var filterVal = filterVals[i];
					filter[filterAttr] = filterVal;
				}
			}
		}
		// 查询数据
		if(query) {
			data = dict.get(dictType, dictName, dictValue, filter);
		}
		
		var dataKeys = dictData.split(',');
		data.forEach(function(item){
			var opt = optpl.render({code: item[dictValue], name: item[dictName]});
			var $opt = $(opt);
			dataKeys.forEach(function(key){
				$opt.data(key, item[key]);
			})
			$select.append($opt);
		})
		form.render();
	}
	
	// 联动后的onchange事件
	function onchange($this) {
		var key = $this.attr('lay-filter');
		var widgets = CASCADE[key];// 受影响的控件
		widgets.forEach(function(widget){
			var $widget = $(widget);
			if($widget.is('select')) {
				console.log('select on change sub widget: '+widget.name)
				initSelect(widget, false);
			} else if($widget.is('input')) {//
				var $option = $this.find('option:selected');
				$widget.val($option.data($widget.attr('dict-value'))).trigger('change');
			} else if($widget.data('widget') == 'checkbox' || $widget.data('radio')) {
				console.log('widget on change sub widget: '+$widget.data('name'))
				initCheckbox(widget, false);
			}else {
				throw '暂不支持该组件的联动：'+widget.tagName;
			}
		})
	}
	// checkbox,radio
	var checkboxtpl = layui.laytpl('<input type="{{d.widget}}" name="{{d.name}}" value="{{d.value}}" title="{{d.title}}"/>');
	function initCheckbox(widget, first) {
		var $widget = $(widget);
		$widget.empty();
		var wdata = $widget.data();
		var checkedVals = [];
		if(wdata.checked && wdata.checked != "") {
			//避免出现数字，然后split方法报错
			wdata.checked = wdata.checked+"";
			checkedVals = wdata.checked.split(",");
			for(var i in checkedVals) {
				checkedVals[i] = checkedVals[i].trim();
			}
			delete wdata.checked;
		}
		
		var dictType = attr($widget, 'dict-type');
		debugger;
		var dictFilter = attr($widget, 'dict-filter', null);
		var dictName = attr($widget, 'dict-name', 'name');
		var dictValue = attr($widget, 'dict-value', 'code');
		var dictParam = attr($widget, 'dict-param', 'filter');
		var layFilter = attr($widget, 'lay-filter', '');
		var data = [];
		var query = true; // 是否查询数据
		// 根据dict-fitler联动
		var filter = {};// key-下拉框对应数据集的属性、value-过滤值
		var filterAttrs = dictParam.split(',');
		if(dictFilter && dictFilter.indexOf("elem:") == 0) {// 联动下拉框的上级下拉框选择符
			var filterIds = dictFilter.substring(5).split(',');
			for(var i in filterIds) {
				var filterId = filterIds[i];
				var filterAttr = filterAttrs[i];
				if(first === true) {
					if(CASCADE[filterId]) {
						CASCADE[filterId].push(widget);
					}else {
						CASCADE[filterId] = [widget];
					}
				}
				var $primary = $('[lay-filter='+filterId+']');
				var filterVal = $primary.val();
				if(filterVal && filterVal != "") {
					filter[filterAttr] = filterVal;
				}else {// 联动条件不满足，不走查询
					query = false;
				}
			}
		}else {
			if(dictFilter) {
				var filterVals = dictFilter.split(',');
				for(var i in filterAttrs) {
					var filterAttr = filterAttrs[i];
					var filterVal = filterVals[i];
					filter[filterAttr] = filterVal;
				}
			}
		}
		// 查询数据
		if(query) {
			data = dict.get(dictType, dictName, dictValue, filter);
		}
		console.log(data);
		data.forEach(function(item, index){
			var d = $.extend(wdata, {value: item[dictValue], title: item[dictName]}) 
			var $input = $(checkboxtpl.render(d));
			if(checkedVals.indexOf(item[dictValue]) >= 0) {
				$input.attr('checked', true);
			}
			if(d.layFilter) $input.attr('lay-filter', d.layFilter);
//			if(d.layVerify) $input.attr('lay-verify', d.layVerify);
			$widget.append($input);
		});
		form.render();
	}
	
	function initInputCascade(input) {
		debugger;
		var $input = $(input);
		var dictFilter = attr($input, 'dict-filter', null);
		var dictValue = attr($input, 'dict-value', null);
		if(dictFilter && dictFilter.indexOf("elem:") == 0) {// 联动下拉框的上级下拉框选择符
			var filterId = dictFilter.substring(5);
			if(CASCADE[filterId]) {
				CASCADE[filterId].push(input);
			}else {
				CASCADE[filterId] = [input];
			}
		}
	}
	
	function render(selector) {
		var parent = selector;
		if(!parent) {
			parent = '*';
			CASCADE = {};
		}
		// 绑定下拉框值
		$(parent).find('select[dict-type]').each(function(){initSelect(this, true)});
		// 下拉框扩展值
		$(parent).find('input[dict-filter]').each(function() {initInputCascade(this)});
		// 初始化checkbox/radio
		$(parent).find('div[dict-type]').each(function() {initCheckbox(this, true)});
		
		// 遍历CASCADE并绑定事件
		for(var filterId in CASCADE) {
			if(CASCADE_EVENT[filterId] === true) continue; // 已有联动事件的，不再重复绑定
			console.log('绑定联动事件:')
			console.log(filterId);
			console.log(CASCADE[filterId]);
			var $primary = $('[lay-filter='+filterId+']');
			if($primary.is('input')) {
				$primary.change(function() {
					console.log('input.change');
					var $this = $(this);
					onchange($this);
				})
			}else {
				var type = 'select';
				form.on(type+'('+filterId+')', function(data){
					var $this = $(data.elem);
					onchange($this);
				});
			}
			CASCADE_EVENT[filterId] = true;
		}
	}
	
	function indexOf(array, val) {
		for(var i = 0; i < array.length; i++) {
			if(array[i] === val) return i;
		}
		return -1;
	}
	
	function val(id, data) {
		
		if(!data) {
			var result = form.val(id);
			for(var name in result) {
				$form.find('input:checkbox[name='+name+']:checked').each(function(i){
					var checkbox = this;
					var value = checkbox.value;
					if(i === 0) {
						result[name] = value;
					}else if(i === 1){
						var array0 = result[name];
 						result[name] = [];
 						result[name].push(array0);
						result[name].push(value);
					}else {
						result[name].push(value);
					}
				});
			}
			console.log(result);
			return result;
		}
		
		$form = $('[lay-filter='+id+']');
		for(var name in data) {
			$form.find('[name='+name+']').each(function(){
				var $widget = $(this);
				if($widget.is('select')) {// 模拟下拉框选择事件，用以初始化联动下拉框
					var value = data[name];
					if(!value || value === '') value = '""';
					$widget.siblings("div.layui-form-select").find('dl dd[lay-value=' + value + ']').click();
				}
			});
		}
		form.val(id, data);
		
		for(var name in data) {
			var vals = data[name];
			if(!vals) vals = [];
			$form.find('input:checkbox[name='+name+']').each(function(){
				var checkbox =this;
				if(indexOf(vals, checkbox.value) >= 0) {
					checkbox.checked = true;
				}else {
					checkbox.checked = false;
				}
			});
		}
		
		form.render();
		
	}
	
	function data(filterId) {
		var $widget = $('[lay-filter='+filterId+']');
		if($widget.is('select')) {
			var $option = $widget.find('option:selected');
			return $option.data();
		}else {
			return $widget.data();
		}
	}
	
	function select(filterId, index) {
		var $widget = $('[lay-filter='+filterId+']');
		var name = $widget.attr('name');
		var option = $widget.find('option').get(index);
		var value = option.value;
		var $form = $widget.parents('.layui-form');
		var formId = $form.attr('lay-filter');
		var data = {};
		data[name] = value;
		this.val(formId, data);
	}
	
	// 初始化
	render();
	
	
	exports('xform', {render: render, val: val, data: data, select: select});
});
