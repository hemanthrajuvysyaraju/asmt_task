
var array = {};
function addtocart(but) {
	if (localStorage.getItem("cart") == null||localStorage.getItem("cart") == 'null') {
		let json = JSON.stringify(array);
		localStorage.removeItem("cart");
		localStorage.setItem("cart", json);
	}
	
	array = JSON.parse(localStorage.getItem("cart"));
	var id = $(but).parent().parent().attr('id');
	var name = $(but).parent().parent().find('.desc').children().text();
	var image = $(but).parent().parent().find('.image').children().attr('src');
	var qty = $(but).closest(".btn").find('select').val();
	var price = $(but).closest(".btn").find('#price').val();
	var total = parseFloat(price) * parseInt(qty);
	var prod = [id, name, image, qty, price, total];
	var pin=$(but).closest(".btn").find('#pincode').val();
		$.ajax({
		url:"./checkpincode",
		type:'POST',
		data:
		{
			pincode:pin,
			productid:id
		},
		success:function(result)
		{
			console.log(result);
			if(result.status==true)
			{
				array[id] = prod;
				let json = JSON.stringify(array);
				localStorage.removeItem("cart");
				localStorage.setItem("cart", json);
				$(".itemcounter").html(Object.keys(array).length)
			}
			else{
				alert("sorry item is not servicable at that pincode")
			}
		}
	})
}
function removeitem(but)
{
	var id=$(but).parent().parent().closest('.product').attr('id');
	array = JSON.parse(localStorage.getItem("cart"));
	delete	array[id];
	let json = JSON.stringify(array);
	localStorage.removeItem("cart");
	localStorage.setItem("cart", json);
	$(".itemcounter").html(Object.keys(array).length);
	viewcart();
}
function viewcart()
{
	var prods = JSON.parse(localStorage.getItem("cart"));
			let htmlcd = ``;
			var total = 0;
			$.each(prods, function(i, val) {
				htmlcd += `<div class="product" id="`+val[0]+`">
										<div class="image">
											<img src="`+ val[2] + `"alt="` + val[1] + `" image" />
										</div>
										<div class="desc">
											<p>`+val[1] + `</p>
										</div>
										<div class="btn">
											<p class="price">
												price:`+ val[4] + `$
											</p>
											<p>quantity`+val[4]+`</p>
											<b>total:`+ val[5] + `</b>
											<button onclick="removeitem(this)">remove</button>
										</div>
									</div>`;
				total += val[5];
			})
			htmlcd+=`<h3>have copuon code?</h3><input type="text" name="cpncode" id='cpncode' ><input type="button" id="apply" value="apply code" onclick="applycpn()"><div id="cpndiv"></div>`;
			htmlcd += `<div><h2>grand total of your cart is::<span id="total">`+total+`</span><button  id="checkout" onclick="checkout()" >checkout</button></div>`;
			$("#cartprods").html(htmlcd);
			$("#cartprods").show();
			$("#all").hide();
			$("#categorywise").hide();
			$('footer').hide();
}
function loading(){
	var a={};
	a=JSON.stringify(a);
	localStorage.setItem("cart",a);
	array = JSON.parse(localStorage.getItem("cart"));
	if(array==null||array==undefined)
	{
		$(".itemcounter").html(0);
	}
	else
	{
		$(".itemcounter").html(Object.keys(array).length);		
	}
	$(document).ready(function() {
		$("#cart").on('click', function () {
			
			$('#apply').on('click',function()
			{
				
			})
			var prods = JSON.parse(localStorage.getItem("cart"));
			let htmlcd = ``;
			var total = 0;
			$.each(prods, function(i, val) {
				htmlcd += `<div class="product" id="`+val[0]+`">
										<div class="image">
											<img src="`+ val[2] + `"alt="` + val[1] + `" image" />
										</div>
										<div class="desc">
											<p>`+ val[1] + `</p>
										</div>
										<div class="btn">
											<p class="price">
												price:`+ val[4] + `$
											</p>
											<b>total:`+ val[5] + `
											<button onclick="removeitem(this)">remove</button>
										</div>
									</div>`;
				total += val[5];
			})
			htmlcd+=`<h3>have copuon code?</h3><input type="text" name="cpncode" id="cpncode" ><input type="button" id='apply'value="apply code" onclick="applycpn()"><div id="cpndiv"></div>`;
			htmlcd += `<h2>grand total of your cart is::$<span id="total">`+total+`</span><p style="color:rgb(0,0,0,0.5)">taxes applicable</p></h2>`+`<button onclick="checkout()" type="submit">checkout</button>`;
			$("#cartprods").html(htmlcd);
			$("#cartprods").show();
			$("#all").hide();
			$("#categorywise").hide();
			$('footer').hide();
		}),
			$("#home").on('click', function() {
				$(".container").hide();
				$("#all").show();
				$("#categorywise").hide();
				$("#cartprods").hide();
				$('footer').show();
			}),
			$("#category").on('change', function() {
				$.ajax(
					{
						url: 'http://localhost:8080/shoppingcartmvc/getcategoryproducts',
						type: 'POST',
						data:
						{
							categoryid: $('#category').val()
						},
						success: function(result) {
							console.log($('#category').val());
							console.log(result);
							$("#all").hide();
							$("#cartprods").hide();
							let htmlopt = ``;
							$.each(result.product_id, function(i, val) {
								htmlopt += `<div class="product" id="` + val + `">
									<div class="image">
										<img src="`+ result.product_image[i] + `"
											alt="`+ result.product_name[i] + `"+" image" />
									</div>
									<div class="desc">
										<p>`+ result.product_name[i] + `</p>
									</div>
									<div class="btn">
									<input type="text" id="price" value="`+ result.product_price[i] + `" hidden>
										<p class="price">
											price:`+ result.product_price[i] + `$
										</p>
										<select name="quantity">
										<option value=1>1</option>
										<option value=2>2</option>
										<option value=3>3</option>
										<option value=4>4</option>
										<option value=5>5</option>
										</select>
										<label for="pincode">pincode::</label><input id="pincode" type="number" name="pincode" placeholder="enter pincode">
										<button onclick="addtocart(this)">add to cart</button>
									</div>
								</div>`;
							});
							$("#categorywise").html(htmlopt);
							$("#categorywise").show();
							$("#cartprods").hide();
							$('#all').hide();
							$('footer').hide();
						}
					}
				)

			});
			$("#sort").on('change', function() {
				$.ajax(
					{
						url: './sort',
						type: 'POST',
						data:
						{
							sorttype: $('#sort').val(),
							categoryid:$('#category').val()
							
						},
						success: function(result) {
							console.log($('#sort').val());
							console.log(result);
							$("#all").hide();
							$("#cartprods").hide();
							let htmlopt = ``;
							$.each(result.product_id, function(i, val) {
								htmlopt += `<div class="product" id="` + val + `">
									<div class="image">
										<img src="`+ result.product_image[i] + `"
											alt="`+ result.product_name[i] + `"+" image" />
									</div>
									<div class="desc">
										<p>`+ result.product_name[i] + `</p>
									</div>
									<div class="btn">
									<input type="text" id="price" value="`+ result.product_price[i] + `" hidden>
										<p class="price">
											price:`+ result.product_price[i] + `$
										</p>
										<select name="quantity">
										<option value=1>1</option>
										<option value=2>2</option>
										<option value=3>3</option>
										<option value=4>4</option>
										<option value=5>5</option>
										</select>
										<label for="pincode">pincode::</label><input id="pincode" type="number" name="pincode" placeholder="enter pincode">
										<button onclick="addtocart(this)">add to cart</button>
									</div>
								</div>`;
							});
							$("#categorywise").html(htmlopt);
							$("#categorywise").show();
							$("#cartprods").hide();
							$('#all').hide();
							$('footer').hide();
						}
					}
				)

			});
	});
}
function checkout()
{
	if(localStorage.getItem("cart").length>2)
	{
		$.ajax(
			{
				url:"http://localhost:8080/shoppingcartmvc/checkout",
				type:"POST",
				data:
				{
					cart:localStorage.getItem("cart"),
					cpncode:$('#cpncode').val()
				},
				success:function(result)
				{
					window.location.href="http://localhost:8080/shoppingcartmvc/checkout.jsp"
				}
			}
		)
		
	}
	else{
		alert("you need to add items to checkout");
	}
	localStorage.removeItem("cart");
}
function applycpn()
{
	$.ajax({
		url:"./checkcoupon",
		type:"POST",
		data:{
			cpncode:$('#cpncode').val()
		},
		success:function(result)
		{
			console.log(result);
			if(result.validity==true)
			{
				if($('#total').text()>result.min_value)
				{
					$('#total').text($('#total').text()-result.value);
					$('#cpndiv').text('');
				}
			}
			else
			{
				$('#cpndiv').text("invalid coupon");
			}
		}
	})
}