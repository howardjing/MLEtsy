x = seq(-4, 24, length=200);
y = dnorm(x, mean = 0, sd = 1);
plot(x, y, type = 'l', col = "red");

# plot everything by looping
for ( i in 1:10) {
	lines(x, dnorm(x, mean = 2*i, sd = 1));
}
