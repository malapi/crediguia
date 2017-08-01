 function siguiente(nextPage){
		    	 //alert("lala");

		    	/* $.mobile.pageContainer.pagecontainer('change', nextPage, {
			    	  transition: 'flip',
			    	  changeHash: true,
			          reverse: true,
			          showLoadMsg: true,
			          reload : true
			    	});*/

		    	 $.mobile.changePage($(nextPage),{reload:true,transition: 'flip',});

		     }

  jQuery.fn.serializeObject = function() {
  var arrayData, objectData;
  arrayData = this.serializeArray();
  objectData = {};

  $.each(arrayData, function() {
    var value;

    if (this.value != null) {
      value = this.value;
    } else {
      value = '';
    }

    if (objectData[this.name] != null) {
      if (!objectData[this.name].push) {
        objectData[this.name] = [objectData[this.name]];
      }

      objectData[this.name].push(value);
    } else {
      objectData[this.name] = value;
    }
  });

  return objectData;
};