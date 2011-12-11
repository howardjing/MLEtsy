
# input: csv formatted results
# output: num of items labelled 1
countUserItems <- function(userInfo) {
	count = sum(userInfo$label);
	return(count);
}

#output num of items labelled 0
countRandomItems <- function(userInfo) {
	count = countUserItems(userInfo);
	return (length(userInfo$label) - count);
}

# get the number of items labelled 1 outside the cutoff
countMislabelledUserItems <- function(userInfo) {
	cutOff = countUserItems(userInfo);
	removeTopCutOff <- userInfo$label[cutOff:length(userInfo$label)];
	count = sum(removeTopCutOff);
	return(count);
}

countCorrectlyLabelledUserItems <- function(userInfo) {
	total = countUserItems(userInfo);
	mislabelled = countMislabelledUserItems(userInfo);
	correct = total-mislabelled;
	return(correct);
}

proportionRight <- function(userInfo) {
	total = countUserItems(userInfo);
	numWrong = countMislabelledUserItems(userInfo);
	numRight = total-numWrong;
	return(numRight/total);
}

errorSummary <- function(userInfo) {
	
	total <- length(userInfo$label);
	userItem <- countUserItems(userInfo);
	randomItem <- countRandomItems(userInfo);
	mislabelled <- countMislabelledUserItems(userInfo);
	correct <- countCorrectlyLabelledUserItems(userInfo);
	
	return(list(total=total, userItem=userItem, randomItem=randomItem, mislabelled=mislabelled, correct=correct));
}

error12 <- errorSummary(read.csv(file="results12"));
error13 <- errorSummary(read.csv(file="results13"));
error14 <- errorSummary(read.csv(file="results14"));
#error15 <- errorSummary(read.csv(file="results15"));
error21 <- errorSummary(read.csv(file="results21"));
error23 <- errorSummary(read.csv(file="results23"));
error24 <- errorSummary(read.csv(file="results24"));
#error25 <- errorSummary(read.csv(file="results25"));
error31 <- errorSummary(read.csv(file="results31"));
error32 <- errorSummary(read.csv(file="results32"));
error34 <- errorSummary(read.csv(file="results34"));
#error35 <- errorSummary(read.csv(file="results35"));
error41 <- errorSummary(read.csv(file="results41"));
error42 <- errorSummary(read.csv(file="results42"));
error43 <- errorSummary(read.csv(file="results43"));
#error45 <- errorSummary(read.csv(file="results45"));
#error51 <- errorSummary(read.csv(file="results51"));
#error52 <- errorSummary(read.csv(file="results52"));
#error53 <- errorSummary(read.csv(file="results53"));
#error54 <- errorSummary(read.csv(file="results54"));

#error12.alt <- errorSummary(read.csv(file="filteredResults12"));
#error13.alt <- errorSummary(read.csv(file="filteredResults13"));
#error14.alt <- errorSummary(read.csv(file="filteredResults14"));
##error15.alt <- errorSummary(read.csv(file="filteredResults15"));
#error21.alt <- errorSummary(read.csv(file="filteredResults21"));
#error23.alt <- errorSummary(read.csv(file="filteredResults23"));
#error24.alt <- errorSummary(read.csv(file="filteredResults24"));
##error25.alt <- errorSummary(read.csv(file="filteredResults25"));
#error31.alt <- errorSummary(read.csv(file="filteredResults31"));
#error32.alt <- errorSummary(read.csv(file="filteredResults32"));
#error34.alt <- errorSummary(read.csv(file="filteredResults34"));
##error35.alt <- errorSummary(read.csv(file="filteredResults35"));
#error41.alt <- errorSummary(read.csv(file="filteredResults41"));
#error42.alt <- errorSummary(read.csv(file="filteredResults42"));
#error43.alt <- errorSummary(read.csv(file="filteredResults43"));
##error45.alt <- errorSummary(read.csv(file="filteredResults45"));
##error51.alt <- errorSummary(read.csv(file="filteredResults51"));
##error52.alt <- errorSummary(read.csv(file="filteredResults52"));
##error53.alt <- errorSummary(read.csv(file="filteredResults53"));
##error54.alt <- errorSummary(read.csv(file="filteredResults54"));
