layui.define(['jquery', 'laytpl', 'form'], function (exports) {
    var $ = layui.jquery;
    var DEFAULT_URL = '/dict/select?dictTypeCode=';
    var DICT_CACHE = {};
    
    var match = function(row, filter) {
    	for(var key in filter) {
    		var filterVal = filter[key];
    		var filterTags = [];
    		try {
    			filterTags = row[key] + "";
    		}catch(e){
    			console.error('filter标签解析失败：' + row[key]);
    			console.error(e);
    		}
    		if(filterVal && filterTags !== filterVal) {
    			return false;
    		}
    	}
    	return true;
    }
    
    var dict = {
    	find: function(rows, code, dictValue) {
    		if(!dictValue) dictValue = 'code';
    		for (var i = 0; i < rows.length; i++) {
                var row = rows[i];
                if (row[dictValue] === code) return row;
            }
    		return null;
    	},
        format: function (field, dictType, dictName, dictValue) {
            var me = this;
            if (!dictName) dictName = 'name';
            if (!dictValue) dictValue = 'code';
            return function (d) {
                var rows = me.get(dictType, dictName, dictValue);
                var value = d[field];
                if(value.length===0)return '';
                var vals = $.isArray(value)? value: [value];
                for (var i = 0; i < vals.length; i++) {
                	var row = me.find(rows, vals[i], dictValue);
                	if(row != null) vals[i] = row[dictName];
                }
                return vals.join(',');
            }
        },
        copy: function(txt) {
            var input = document.createElement('textarea');
            document.body.appendChild(input);
            input.value = txt;
            input.select();
            if (document.execCommand('copy')) {
                document.execCommand('copy');
            } else {
                if(document.execCommand("copy", false)) {
                }
            }
            document.body.removeChild(input);
        },
        get: function (dictType, dictName, dictValue, filter) {
        	if (!filter) filter = {};
        	if (!dictName) dictName = 'name';
            if (!dictValue) dictValue = 'code';
        	var rows = [];
        	var dict = DICT_CACHE[dictType];
            if (dict) {
            	dict.forEach(function(row) {
            		if(match(row, filter)) {
	            		rows.push(row);
	            	}
            	})
            	return rows;
            }
            var query = {};
            var filterType = 'local';
            var dictUrl = DEFAULT_URL + dictType;
            if (dictType.indexOf("/") >= 0) {// remote filter
            	dictUrl = dictType;
            	filterType = 'remote';
            	query = filter;
            }
            $.ajax({
                url: Feng.ctxPath+dictUrl,
                data: query,
                async: false,
                success: function (result) {
                    var data = result.data;
                    if(filterType === 'local') {
                    	DICT_CACHE[dictType] = data;
                    }
                    data.forEach(function (row) {
                    	if(filterType === 'remote'
                    			|| (filterType === 'local' && match(row, filter))) {
                    		rows.push(row);
                    	}
                    	
                    })
                }
            });
            
            return rows;
        }



    };


    exports('dict', dict);
});
