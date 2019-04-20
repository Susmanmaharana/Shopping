<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<div class="container">
	<div class="row">
	<c:if test="${not empty message}">
	<div class="col-xs-12">
		<div class="alert alert-success alert-dismissible">
				<button type="button" class="close" 
				data-dismiss="alert">&times;</button>
				${message}
		</div>
	</div>
	</c:if>
	
	
	
		<div class="col-md-offset-2 col-md-8">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Product Management</h4>
					</div>
					<div class="panel-body">
					<!-- From element -->
						<sf:form class="form-horizontal" modelAttribute="product"
						action="${contextRoot}/manage/products" method="POST"
						enctype="multipart/form-data">
							<div class="from-group">
								<label class="control-lebel col-md-4" for="name">
								Product Name: 
								</label>
									<div class="col-md-8">
										<sf:input type="text" path="name" id="name" placeholder="Product Name" class="form-control"/>
										<sf:errors path="name" cssClass="help-block" element="em"/>
										 <!-- <em class="help-block">Please enter Product Name</em> -->
									</div>
							</div>
								<div class="from-group">
								<label class="control-lebel col-md-4" for="brand">
								Brand Name: 
								</label>
									<div class="col-md-8">
										<sf:input type="text" path="brand" id="brand" placeholder="Brand Name" class="form-control"/>
										<sf:errors path="brand" cssClass="help-block" element="em"/>
									<!-- 	 <em class="help-block">Please enter Brand Name !</em> -->
									</div>
							</div>
							
							<div class="from-group">
								<label class="control-lebel col-md-4" for="description">
								Description: 
								</label>
									<div class="col-md-8">
										<sf:textarea path="description" id="description" rows="4" placeholder="Write Descrption for the productList" class="form-control"/>
										 <sf:errors path="description" cssClass="help-block" element="em"/>
										 <!-- <em class="help-block">Please enter Description !</em> -->
									</div>
							</div>
							
							
							<div class="from-group">
								<label class="control-lebel col-md-4" for="unitPrice">
								Unit Price: 
								</label>
									<div class="col-md-8">
										<sf:input type="number"  step="0.01" path="unitPrice" id="unitPrice" placeholder="Unit Price as Ruppes" class="form-control"/>
										 <sf:errors path="unitPrice" cssClass="help-block" element="em"/>
								
										 <!-- <em class="help-block">Please enter Unit Price !</em> -->
									</div>
							</div>
							
								<div class="from-group">
								<label class="control-lebel col-md-4" for="quantity">
								Quantity:  
								</label>
									<div class="col-md-8">
										<sf:input type="number" path="quantity" id="quantity" placeholder="Quantity Available" class="form-control"/>
										<sf:errors path="quantity" cssClass="help-block" element="em"/>
								
										<!--  <em class="help-block">Please enter Quantity !</em> -->
									</div>
							</div>
							<br/>
							
							<div class="from-group">
								<label class="control-lebel col-md-4" for="file">
								Upload a Image file: 
								</label>
									<div class="col-md-8">
										<sf:input type="file" path="file" id="file"   class="form-control"/>
										<sf:errors path="file" cssClass="help-block" element="em"/>
									</div>
							</div>
							
							<div class="from-group">
								<label class="control-lebel col-md-4" for="categoryId">
								Select Category: 
								</label>
									<div class="col-md-8">
										<sf:select class="form-control" id="categoryId" path="categoryId" items="${categories}" itemLabel="name" itemValue="id" />
										<c:if test="${product.id==0}">
										<div class="text-right">
										<br/>
										<button type="button" data-toggle="modal" data-target="#myCategoryModal" class="btn btn-warning btn-xs">Add Category</button>
										</div> 
										</c:if>
									
									<!-- 	 <em class="help-block">Please Choose a image file !</em> -->
									</div>
							</div>
							
							<div class="from-group">
									<div class="col-md-offset-4 col-md-8">
										<input type="submit" name="submit" id="submit" value="Submit" class="btn btn-primary"/>
									<!-- hidden field -->
									<sf:hidden path="id"/>
									<sf:hidden path="code"/>
									<sf:hidden path="active"/>
									<sf:hidden path="supplierId"/>
									<sf:hidden path="purchases"/>
									<sf:hidden path="views"/>
									
									</div>
							</div>
						</sf:form>
				</div>
			</div>
		</div>
	</div>
	
	<div class="row">
		<div class="col-xs-12">
			<h3>Available Products</h3>
			<hr/>
		</div>
		<div class="col-xs-12">
			<div class="container-fluid">
			<div class="table-responsive">
			<!-- table for admin -->
			<table id="adminProductsTable" class="table table-striped table-borded">
			<thead>
				<tr>
					<th>ID</th>
					<th>&#160;</th>
					<th>NAME</th>
					<th>Brand</th>
					<th>QUANTITY</th>
					<th>UNIT PRICE</th>
					<th>ACTIVE</th>
					<th>EDIT</th>
				</tr>
			</thead>
			<%-- <tbody>
				<tr>
					<td>108</td>
					<td>
						<img class="adminDataTableImg" src="${contextRoot}/resources/images/OLSAUD23HS98.jpg" alt="aspire 4930"/>
					</td>
					<td>aspire 4930</td>
					<td>acer</td>
					<td>1</td>
					<td>&#8377;  35450.87 /-</td>
					<td>
							<label class="switch">
								<input type="checkbox" checked="checked" value="108"/>
								<div class="slider"></div>
							</label>
					</td>
					<td>
							<a href="${contextRoot}/manage/108/product" class="btn btn-warning">
									<span class="glyphicon glyphicon-pencil"></span>
							</a>
					</td>
				</tr>
				
					<tr>
					<td>108</td>
					<td>
						<img class="adminDataTableImg" src="${contextRoot}/resources/images/OLSAUD23HS98.jpg" alt="aspire 4930"/>
					</td>
					<td>aspire 4930</td>
						<td>acer</td>
					<td>1</td>
					<td>&#8377;  35450.87 /-</td>
					<td>
							<label class="switch"> 
								<input type="checkbox"  value="108"/>
								<div class="slider"></div>
							</label>
					</td>
					<td>
							<a href="${contextRoot}/manage/108/product" class="btn btn-warning">
									<span class="glyphicon glyphicon-pencil"></span>
							</a>
					</td>
				</tr>
				
			</tbody> --%>
			<tfoot>
				<tr>
					<th>ID</th>
					<th>&#160;</th>
					<th>NAME</th>
					<th>Brand</th>
					<th>QUANTITY</th>
					<th>UNIT PRICE</th>
					<th>ACTIVE</th>
					<th>EDIT</th>
				</tr>
			</tfoot>
			</table>
			</div>
			</div>
			
		
	</div>		
	
	<div class="modal fade" id="myCategoryModal" role="dialog" tabindex="-1">
		<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
				<span>&times;</span>
				</button>
				<h4 class="modal-title">Add New Category</h4>
			</div>
			<div class="modal-body">
				<!-- category form -->
				<sf:form id="categoryForm" modelAttribute="category"
					action="${contextRoot}/manage/category"
					method="POST" class="form-horizontal">
				<div class="from-group">
					<label class="control-label col-md-4" for="category_name">
					Category Name: 
						</label>
						<div class="col-md-8">
							<sf:input type="text" path="name"  id="category_name" class="form-control"/>
						</div>
				</div>
				<div class="from-group">
					<label class="control-label col-md-4" for="category_description">
					Category Description: 
						</label>
						<div class="col-md-8">
							<sf:textarea  rows="3" path="description"  id="category_description" class="form-control"/>
						</div>
				</div>
				<div class="from-group">
						<div class="col-md-offset-4 col-md-8">
							<input type="submit" class="btn btn-primary" value="Add Category"/> 
						</div>
				</div>
				</sf:form>
			</div>
		</div>
		</div>
	</div>
</div>