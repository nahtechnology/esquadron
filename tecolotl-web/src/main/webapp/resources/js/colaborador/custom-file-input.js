
'use strict';

;( function ( document, window, index )
{
	var inputs = document.querySelectorAll( '.inputfile' );

	Array.prototype.forEach.call( inputs, function( input )
	{
		var label	 = input.nextElementSibling,
			label2 = input.nextElementSibling,
			labelVal = label.innerHTML;
		var tam=input;
		input.addEventListener( 'change', function( e )
		{
			var fileName = '';
			var tama = '';
			// var tama = inputs;
			for (var i = 0; i < tam.files.length; i++) {
				var file = tam.files[i];

				if ('size' in file) {
					tama += file.size;
				}
			}
			if( this.files && this.files.length > 1 )
				fileName = ( this.getAttribute( 'data-multiple-caption' ) || '' ).replace( '{count}', this.files.length );
			else
				fileName = e.target.value.split( '\\' ).pop();

			if( fileName ){
				label.querySelector( 'span' ).innerHTML = fileName ;
				document.getElementById("demo").innerHTML = (tama)/1000 +" bytes";}
			else
				label.innerHTML = labelVal;
		});
		// Firefox bug fix
		input.addEventListener( 'focus', function(){ input.classList.add( 'has-focus' ); });
		input.addEventListener( 'blur', function(){ input.classList.remove( 'has-focus' ); });
	});
}( document, window, 0 ));