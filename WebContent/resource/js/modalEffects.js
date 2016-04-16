/**
 * modalEffects.js v1.0.0
 * http://www.codrops.com
 *
 * Licensed under the MIT license.
 * http://www.opensource.org/licenses/mit-license.php
 * 
 * Copyright 2013, Codrops
 * http://www.codrops.com
 */
var ModalEffects = (function() {

	function init() {

		var overlay = $('.md-overlay');

		$('.md-trigger').each(function(el, i) {
			var modal = $('#' + $(this).attr('data-modal'));
			var	close = $(modal).find('.md-close');

			$(this).on('click', function( ev ) {
				$(modal).addClass('md-show');
			});

			$(close).on('click', function( ev ) {
				$(modal).removeClass('md-show');
			});

		} );

	}

	init();

})();