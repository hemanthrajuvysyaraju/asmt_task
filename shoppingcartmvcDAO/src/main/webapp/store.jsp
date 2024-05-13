<%@page import="com.pennant.shoppingcart.ServiceFactory.ServiceFactory"%>
<%@page import="com.pennant.shoppingcart.models.CustomerModel"%>
<%@page import="com.pennant.shoppingcart.models.CategoryModel"%>
<%@page import="com.pennant.shoppingcart.models.CategoryListModel"%>
<%@page import="com.pennant.shoppingcart.models.ProductModel"%>
<%@page import="com.pennant.shoppingcart.models.ProductListModel"%>
<%@page import="jakarta.servlet.http.Cookie"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.io.IOException"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Pennantshopping</title>
<script type="text/javascript" src="jqueryscript.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="homepagestyle.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
</head>



<body onload="loading()">
	<script type="text/javascript">
		
	</script>
	<header>
		<div id="logo-div">
			<img src="Pennantlogo.png" id="logo" alt="pennant logo">
		</div>
		<div>
			<h1>pennkart</h1>
		</div>


		<div id="cart" style="cursor: pointer;">
			<h4 class="itemcounter"></h4>
			cart<i class="fa fa-shopping-cart"></i>
		</div>
		</div>
	</header>
	<div class="sorting-bar">
		<div>
			<p id="home" style="cursor: pointer">home</p>
		</div>
		<div>
			<label>category:</label> <select id="category" name="category">
				<option value="" disabled="disabled" selected hidden>
					categories</option>
				<%
				CategoryListModel categories = ServiceFactory.getCategoryImpl().getCategories();
				for (CategoryModel category : categories) {
				%>
				<option id="<%=category.getCat_id()%>"
					value="<%=category.getCat_id()%>"><%=category.getCat_name()%></option>
				<%
				}
				%>
			</select>
		</div>
		<div>
			<label>sort:</label> <select id="sort" name="sort">
				<option disabled="disabled" selected hidden>sort by</option>
				<option value="ASC">sort by price ascending</option>
				<option value="DESC">sort by price descscending</option>
			</select>
		</div>
	</div>
	<div id="home" class="homescreen">
		<div id="all" class="allproducts">
			<%
			ProductListModel products = ServiceFactory.getProductsImpl().getProducts();
			for (ProductModel product : products) {
			%>
			<div class="product" id="<%=product.getProd_Id()%>">
				<div class="image">
					<img src=<%=product.getProd_Image()%>
						alt="<%=product.getProd_Name()%> image" />
				</div>
				<div class="desc">
					<p><%=product.getProd_Name()%></p>
				</div>
				<div class="btn">
					<label for="price">price:</label> <input type="text" id="price"
						value="<%=product.getProd_Price()%>" hidden>
					<p class="price">

						<%=product.getProd_Price()%>$
					</p>
					<select name="quantity">
						<option value=1>1</option>
						<option value=2>2</option>
						<option value=3>3</option>
						<option value=4>4</option>
						<option value=5>5</option>
					</select> <label for="pincode">pincode::</label><input id="pincode"
						type="number" name="pincode" placeholder="enter pincode">
					<button onclick="addtocart(this)">add to cart</button>
				</div>
			</div>
			<%
			}
			;
			%>
		</div>
		<div id="categorywise"></div>
		<div id="cartprods"></div>
	</div>
</body>
</html>