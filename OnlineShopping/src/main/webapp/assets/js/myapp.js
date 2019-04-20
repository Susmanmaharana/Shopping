$(function(){
	//solve the active menu problem
	switch(menu){
	case 'About Us':
		$('#about').addClass('active');
		break;
	case 'Contact Us':
		$('#contact').addClass('active');
		break;
	case 'All Products':
		$('#listProducts').addClass('active');
		break;
	case 'Manage Products':
		$('#manageProducts').addClass('active');
		break;
	case 'User Cart':
		$('#userCart').addClass('active');
		break;
	 default:
		 if(menu=="HOME")  break;
		$('#listProducts').addClass('active');
	 $('#a_'+menu).addClass('active');
		break;
	} 
	//to tackel the csrf token
	var token =$('meta[name="_csrf"]').attr('content');
	var header =$('meta[name="_csrf_header"]').attr('content');
	if(token.length > 0 && header.length > 0){
		//set the token header for the ajax request
		$(document).ajaxSend(function(e,xhr,options){
			xhr.setRequestHeader(header,token);
		});
	}
	
	
	
	//code for datatable
	//create a dataset
/*	var products = [
								['1','ABC'],
								['2','EFG'],
								['3','IJK'],
								['4','LMN'],
								['5','OPQ'],
								['6','RST'],
								['7','UVW'],
								['8','XYZ']
							   ];*/
	
	var $table = $('#productListTable');
	//execute the this table code
	if($table.length){
		
		var jsonUrl='';
		if(window.categoryId==''){
			jsonUrl=window.contextRoot + '/json/data/all/products';
		}else{
			jsonUrl=window.contextRoot + '/json/data/category/'+window.categoryId+'/products';
		}
		//console.log('Inside the table');
		$table.DataTable({
			lengthMenu:    [[3,5,10,-1],['3 records','5 records','10 records','all records']],
			pageLength : 5,
			ajax: {
				url : jsonUrl,
				dataSrc : ''
			},
			
			columns: [
								{
									data : 'code',
									bSortable: false,
									mRender: function(data, type, row){
				 						return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="dataTableImg"/>';
								         }
								},
				 				{
				 					data: 'name',
				 				},
				 				{
				 					data: 'brand',
				 				},
				 				{
				 					data: 'unitPrice',
				 					mRender: function(data, type, row){
				 						return '&#8377  ' + data
				 						
				 					}/*&#x20B9;&#8377*/
				 				},
				 				{
				 					data: 'quantity',
				 					mRender: function(data, type, row){
				 						if(data <  1){
				 							return '<span style="color:red">Out Of Stock!</span>';
				 							}
				 						return data;
				 						}
				 				},
				 				{
				 					data: 'id',
				 					bSortable: false,
				 					mRender: function(data, type, row){
				 						var str='';
				 						str+='<a href="'+window.contextRoot+'/show/'+data+'/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open">View</span></a> &#160;';
				 						if(userRole == 'ADMIN'){
				 							str+='<a href="'+window.contextRoot+'/manage/'+data+'/product" class="btn btn-warning"><span class="glyphicon glyphicon-pencil">Edit</span></a>';
				 						}else{
				 						
				 						if(row.quantity < 1){
				 							str+='<a href="javascript:void(0)"class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart">Add to Cart</span></a>';
				 						}else { 
				 							str+='<a href="'+window.contextRoot+'/cart/add/'+data+'/product"class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart">Add to Cart</span></a>';
				 		   			      }
				 					   } 
				 						return str;  
				 					}
				 				}
							]
		});
	}
	//dismissing the request after 3 sec
	var $alert =$('.alert');
	if($alert.length){
		setTimeout(function(){
			$alert.fadeOut('slow');
		},3000)
	}
	//--------------------------------------------
	$('.switch input[type="checkbox"]').on('change',function(){
		
		var checkbox=$(this);
		var checked= checkbox.prop('checked');
		var dMsg= (checked)? 'You want to activate the product?':
												'You want to deactivate the product';
		var value=checkbox.prop('value');
		bootbox.confirm({
			size: 'medium',
			title: 'Product Activation & Deactivation',
			message: dMsg,
			callback: function(confirmed){
				if(confirmed){
					console.log(value);
					bootbox.alert({
						size: 'medium',
						title: 'Information', 
						message:  'You are going to perform operation on product '+value
					});
				}
				else{
					checkbox.prop('checked', !checked);
				}
			}
		});
	});
					
					
					
					
					
					
					
	
	
	//-------------------------------
	//data table for admin
	//-------------------------------
	var $adminProductsTable = $('#adminProductsTable');
	//execute the this table code
	if($adminProductsTable.length){
		var jsonUrl=window.contextRoot+'/json/data/admin/all/products';
		
		//console.log('Inside the table');
		$adminProductsTable.DataTable({
			lengthMenu:    [[10,30,50,-1],['10 records','30 records','50 records','ALL']],
			pageLength : 30,
			ajax: {
				url : jsonUrl,
				dataSrc : ''
			},
			
			columns: [
								{
									data: 'id',
								},
								{
									data : 'code',
									bShortable: false,
									mRender: function(data, type, row){
				 						return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="adminDataTableImg"/>';
								         }
								},
				 				{
				 					data: 'name',
				 				},
				 				{
				 					data: 'brand',
				 				},
				 			
				 				{
				 					data: 'quantity',
				 					mRender: function(data, type, row){
				 						if(data<1){
				 							return '<span style="color:red">Out Of Stock!</span>';
				 							}
				 						return data;
				 						}
				 				},
				 				{
				 					data: 'unitPrice',
				 					mRender: function(data, type, row){
				 						return '&#8377  ' + data
				 						
				 					}/*&#x20B9;&#8377*/
				 				},
				 				{
				 					data: 'active',
				 					bShortable: false,
				 					mRender: function(data, type, row){
				 					var str='';
				 					str+= '<label class="switch">';
				 					if(data){
				 						str+='<input type="checkbox" checked="checked" value="'+row.id+'"/>';
				 					}else{
				 						str+='<input type="checkbox"  value="'+row.id+'"/>'
				 					}
									str+='<div class="slider"></div></label>';
										return str;
				 					
				 					}
				 				},
				 				{
				 					data: 'id',
				 					bShortable: false,
				 					mRender: function(data, type, row){
					 					var str='';
					 					str+='<a href="'+window.contextRoot+'/manage/'+data+'/product" class="btn btn-warning">';
					 					str+='<span class="glyphicon glyphicon-pencil"></span></a>';
					 							return str;
									}
				 				}
							],
		initComplete: function(){
		var api = this.api();
		api.$('.switch input[type="checkbox"]').on('change',function(){
		
		var checkbox=$(this);
		var checked= checkbox.prop('checked');
		var dMsg= (checked)? 'You want to activate the product?':
												'You want to deactivate the product';
		var value=checkbox.prop('value');
		bootbox.confirm({
			size: 'medium',
			title: 'Product Activation & Deactivation',
			message: dMsg, 
			callback: function(confirmed){
				if(confirmed){
					console.log(value);
					var activationUrl=window.contextRoot+'/manage/product/'+value+'/activation';
					$.post(activationUrl, function(data){
						bootbox.alert({
							size: 'medium',
							title: 'Information',
							message:  data
						});
					});
				}
				else{
					checkbox.prop('checked', !checked);
				}
			}
		});
	});
   }
});
}
	
	//----------------------------------------
	// validation code for category
	//-------------------------------------------
	var $categoryForm =$('#categoryForm')
	if($categoryForm.length){
		
		$categoryForm.validate({
			rules: {
				name:{
									required: true,
									minlength: 2
					   	 },
						
	description: {
									required: true
						}
			},
	  messages:{
							name:{
								required: 'Please add the category name !',
								minlength: 'The category name should not be less than 2 charectors'
							},
		description: {
								required: 'Please add the category description  !'
					       }
		  	         },
		  	         errorElement: 'em',
		  	         errorPlacement: function(error, element){
		  	        	 error.addClass('help-block');
		  	        	 error.insertAfter(element);
		  	         }
		});
	}
	///////////////////////////////////
	///JQuery Validation for login//////
	//////////////////////////////////
	//----------------------------------------
	// validation code for category
	//-------------------------------------------
	var $loginForm =$('#loginForm')
	if($loginForm.length){
		
		$loginForm.validate({
			rules: {
				username:{
									required: true,
									email: true
					   	 },
						
					   	password: {
									required: true
						}
			},
	  message:{
							username:{
								required: 'Please enter the email !',
								email: 'Please enter the  Valid email'
							},
							password: {
								required: 'Please enter the password  !'
					       }
		  	         },
		  	         errorElement: 'em',
		  	         errorPlacement: function(error, element){
		  	        	 error.addClass('help-block');
		  	        	 error.insertAfter(element);
		  	         }
		});
	}
	//----------------------------------------------------------------------
	//-----Handling the click event of refresh cart button
	//----------------------------------------------------------------------
	$('button[name="refreshCart"]').click(function(){
		//fetch the cart line id
		var cartLineId =$(this).attr('value');
		var countElement=$('#count_'+cartLineId);
		var originalCount= countElement.attr('value');
		var currentCount=countElement.val();
		//work when the count has changed
		if(currentCount !== originalCount){
		if(currentCount < 1 || currentCount > 3){
			 
			//reverting back to the original count
			//user has given below 1 and above 3
			countElement.val(originalCount);
			bootbox.alert({
				size: 'medium',
				title: 'Error',
				message: 'product count should be min 1 and max 3'
			});
		}else{
			var updateUrl = window.contextRoot +'/cart/'+cartLineId+'/update?count='+currentCount;
			//forward it to the controller
			window.location.href = updateUrl;
		}
	}
	});
	
	
	
	
	
	
	
});