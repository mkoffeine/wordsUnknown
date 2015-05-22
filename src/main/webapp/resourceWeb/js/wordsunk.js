function test() {
	return "test";
}
var useEnds = true;
var patternLetter = /[a-zA-Z]/;
var patternWord = /[a-zA-Z]{1,35}/;

var knownWords = ["the", "test", "qwer"];
var missedWords = ["hadn", "haven", "doesn", "weren", "wasn", "wouldn", "shouldn", "mustn", "", "needn", "didn", "don",
	"couldn", "can", "aren", "won", "isn", "hasn"];
function runWordGeneration() {
	var textElement = document.getElementById("textID");
	var input = textElement.value.toString();
	var workedWords = document.getElementById("workedWordsID");

	var knownWords = initKnownWordsBeforeAction();
	var i = 0;
	var arrayRes = [""];
	while (i < input.length) {
		if (input[i].match(patternLetter)) {
			var wordOnIndex = getWordOnIndex(i, input);
			var wordOnIndexLowCase = wordOnIndex.toLowerCase();
//			console.log("--- " + wordOnIndex);
			if (wordOnIndex.length < 3 || missedWords.indexOf(wordOnIndexLowCase) > -1) {
				arrayRes.push(wordOnIndex);
			}
			else {
				var isKnownWord = knownWords.indexOf(wordOnIndexLowCase) > -1;
				//? "knownWord":"unKnownWord";
				if (useEnds && !isKnownWord) {
					if (wordOnIndexLowCase.charAt(wordOnIndexLowCase.length - 1) != "s") {
						isKnownWord = knownWords.indexOf(wordOnIndexLowCase + "s") > -1;
					}
					else {
						isKnownWord = knownWords.indexOf(wordOnIndexLowCase.substring(0, wordOnIndexLowCase.length - 1)) > -1;
					}
				}
				var word;
				if (isKnownWord) {
//					word = "<span class=\"knownWord\"  onclick=\"clickOnKnownWord(this);\">" + wordOnIndex + "</span>";
					word = "<span class=\"knownWord\"  >" + wordOnIndex + "</span>";
				}
				else {
					if (isWordCaseCorrect(wordOnIndex)) {
						word = "<span class=\"unKnownWord\" onclick=\"clickOnUnkWord(this);\">" + wordOnIndex + "</span>";
					}
					else {
						word = "<span class=\"outOfSpace\" >" + wordOnIndex + "</span>";
					}
				}
				arrayRes.push(word);
			}
			i += wordOnIndex.length;
		}
		else {
			if (input[i] == '\n') {
				arrayRes.push("<br>");
			}
			else {
				arrayRes.push(input[i]);
			}
			i++;
		}
	}

	workedWords.innerHTML = arrayRes.join("");

//	alert("ssss");
}
function clickOnUnkWord(span) {
	var text = span.textContent.toString();
	var ss = document.getElementsByTagName("span");
	for (var i = 0; i < ss.length; i++) {
//	  if (ss[i].className.toString() == "unKnownWord" && ss[i].textContent.toString().toLowerCase() == text.toLowerCase()) {
		var anyWhereWord = ss[i].textContent.toString().toLowerCase();
		if (ss[i].className.toString() == "unKnownWord" && compareWord(anyWhereWord, text.toLowerCase())) {
			ss[i].setAttribute("class", "knownWord");
		}
		else if (ss[i].className.toString() == "knownWord" && anyWhereWord == text.toLowerCase()) {
			ss[i].setAttribute("class", "notInList");
		}
		else if (ss[i].className.toString() == "notInList" && anyWhereWord == text.toLowerCase()) {
			ss[i].setAttribute("class", "unKnownWord");
		}
	}
}
function compareWord(anyWhereWord, clickedWord) {
	var equal = anyWhereWord == clickedWord;
	if (useEnds && !equal) {
		if (clickedWord.charAt(clickedWord.length - 1) != "s") {
			equal = anyWhereWord == clickedWord + "s";
		}
		else {
			equal = anyWhereWord == clickedWord.substring(0, clickedWord.length - 1);
		}
	}
	return equal;
}

/*function clickOnKnownWord(span) {
 var text = span.textContent.toString();
 var ss = document.getElementsByTagName("span");
 for(var i = 0;i< ss.length;i++){
 if (ss[i].className.toString() == "knownWord" && ss[i].textContent.toString().toLowerCase() == text.toLowerCase()) {
 ss[i].setAttribute("class", "unKnownWord");
 }
 }
 //	alert("word: " + span.textContent.toString() + "  el.length: " + ss.length);
 }*/

function getWordOnIndex(index, input) {
	var s = input.substr(index, 35);
	var res = s.match(patternWord);
	return res[0];
}
function getKnownWordsAfterEditing() {
	var ss = document.getElementsByTagName("span");
	var words = initKnownWordsBeforeAction();
	var wordsUnk = [];
	var wordsUnkFreq = [];
	for (var i = 0; i < ss.length; i++) {
		var w = ss[i].textContent.toString().toLowerCase();
		if (ss[i].className.toString() == "knownWord") {
			if (words.indexOf(w) < 0) {
				words.push(w);
			}
		}
		else if (ss[i].className.toString() == "unKnownWord") {
			if (wordsUnk.indexOf(w) < 0) {
				wordsUnk.push(w);
				wordsUnkFreq.push(1);
			}
			else {
				var index = wordsUnk.indexOf(w);
				wordsUnkFreq[index]++;
			}
		}
	}
	var outputKnownWords = document.getElementById("outputKnownWordsID");
	outputKnownWords.value = words.join(" ");
	var max = 0;
	for (var i = 0; i < wordsUnk.length; i++) {
		if (max < wordsUnkFreq[i]) {
			max = wordsUnkFreq[i];
		}
	}
	var mergedUnkWords = [];
	for (var freq = max; freq > 0; freq--) {
		for (var i = 0; i < wordsUnk.length; i++) {
			if (wordsUnkFreq[i] == freq) {
				mergedUnkWords.push(wordsUnk[i] + " " + wordsUnkFreq[i]);
			}
		}
	}

//	for (var i = 0; i< wordsUnk.length;i++) {
//		mergedUnkWords[i] = wordsUnk[i] + " " + wordsUnkFreq[i];
//	}
	var outputUnKnownWords = document.getElementById("outputUnKnownWordsID");
	outputUnKnownWords.value = mergedUnkWords.join("\n");
	mergedUnkWords = [];
	for (var i = 0; i < wordsUnk.length; i++) {
		mergedUnkWords[i] = wordsUnk[i] + " " + wordsUnkFreq[i];
	}
	var outputUnKnownWords = document.getElementById("outputUnKnownWordsIDNotSorted");
	outputUnKnownWords.value = mergedUnkWords.join("\n");

	createRating();
	saveUnknownWords();
}

function createRating() {

	$.ajax({
		method: "POST",
		data : { words: document.getElementById("outputUnKnownWordsID").value },
		url : 'getFreq',
		success : function(data) {
			$('#rateID').html(data);
		}
	});
}

function saveUnknownWords() {
	$.ajax({
		method: "POST",
		data : { words: document.getElementById("outputKnownWordsID").value },
		url : 'saveWords',
		success : function(data) {
			$('#result').html(data);
		}
	});
}

function initKnownWordsBeforeAction() {
	var knownText = document.getElementById("inputKnownWordsID").value.toString();
//	console.log(knownText);
	return knownText.split(" ");
}

function isWordCaseCorrect(word) {
	var sub = word.substr(1);
	return sub == sub.toLowerCase();
}