 <div class="container">
	<div class="row">
		<div class ="col-md-3">
			 <%@include file="./shared/sidebar.jsp" %>
		</div>
		<!-- to dispaly the actual product -->
		<div class ="col-md-9">
			<!-- to add the breadcrumb component -->
				<div class="row">
						<div class="col-lg-12">
 								<c:if test="${userClickAllProducts==true}">
 									<script>
 										window.categoryId = '';
									 </script>
  									<ol class="breadcrumb">
  										<li><a href="${contextRoot}/home">Home</a></li>
  										<li class="active">All Products</li>
  									</ol>
  								</c:if>
   								<c:if test="${userClickCategoryProducts==true}">
   								 <script>
		 							window.categoryId = '${category.id}';
 								</script>
  									<ol class="breadcrumb">
  										<li><a href="${contextRoot}/home">Home</a></li>
  										<li class="active">Category</li>
   										<li class="active">${category.name}</li>
  									</ol>
 								</c:if>
 					 </div>
  
  
  <!-- Data table  -->

<div class="row">
	<div class="col-xs-12">
	<div class="container-fluid">
			<div class="table-responsive">
			
			<table  id="productListTable" class="table table-striped table-borderd">
			<thead>
					<tr>
						<th></th>	
							<th>NAME</th>	
							<th>BRAND</th>	
							<th>PRICE</th>	
							<th>QUANTITY AVAILABLE</th>	
								<th></th>	
					</tr>
			</thead>
			<tfoot>
					<tr>
							<th></th>	
							<th>NAME</th>	
							<th>BRAND</th>	
							<th>PRICE</th>	
							<th>QUANTITY AVAILABLE</th>	
					</tr>
			</tfoot>
			</table>
			</div>
	
	</div>
		
</div>
</div>





 <%--  <div class="col-lg-12">
 <c:if test="${userClickAllProducts==true}">
  <ol class="breadcrumb">
  <li><a href="${contextRoot}/home">Home</a></li>
  <li class="active">All Products</li>
  </ol>
  </c:if>
   <c:if test="${userClickCategoryProducts==true}">
  <ol class="breadcrumb">
  <li><a href="${contextRoot}/home">Home</a></li>
  <li class="active">Category</li>
   <li class="active">${category.name}</li>
  </ol>
  </c:if>
  </div> --%>

</div>
</div>
</div>
</div>
