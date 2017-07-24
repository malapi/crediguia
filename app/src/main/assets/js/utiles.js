// JavaScript Document	
//http://stackoverflow.com/questions/391979/get-client-ip-using-just-javascript
//var mmjsCountryCode = geoip_country_code();
//var mmjsCountryName = geoip_country_name();


$.widget( "ui.tabs", $.ui.tabs, {

    _createWidget: function( options, element ) {
        var page, delayedCreate,
            that = this;

        if ( $.mobile.page ) {
            page = $( element )
                .parents( ":jqmData(role='page'),:mobile-page" )
                .first();

            if ( page.length > 0 && !page.hasClass( "ui-page-active" ) ) {
                delayedCreate = this._super;
                page.one( "pagebeforeshow", function() {
                    delayedCreate.call( that, options, element );
                });
            }
        } else {
            return this._super();
        }
    }
});
$(document).ready(function() {
	
	});





$(document).on("pageshow",".air",function(){
	caargarEstructuraPagina($(this));
			
});


