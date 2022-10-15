const url = `https://chart.googleapis.com/chart?cht=qr&chs=300x300&chl=${document.URL}`;
el("#qrcode").src = url;
el("h4").innerHTML = document.URL;

function el(selector) {
	return document.querySelector(selector);
}