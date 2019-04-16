
	function submitForm(originator)
    {
    	if( !confirm("You are about to place your order. Do you wish to continue?") == true ) {
    		originator.submit();
    	}
    	
    	var pN=originator.parentNode;
        while (true)
        {
            if (pN&&pN.nodeName=='FORM')
            {
                pN.submit();
                break;
            }
            pN=pN.parentNode;
        }
    }

