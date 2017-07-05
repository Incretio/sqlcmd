function addFieldName(parentId) {
    var parent = addElementP(parentId);
	addLabel("Имя столбца: ", parent);
	addInputText("columns", parent);
}

function addLine(parentId) {
	var parent = addElementP(parentId); 
	addLabel("Имя столбца: ", parent);
	addInputText("columns", parent);
	addLabel(" Значение столбца: ", parent);
	addInputText("values", parent);
}

function addElementP(parentId) {     
	var elementP = document.createElement("p");
	document.getElementById(parentId).appendChild(elementP);  
	return elementP;
}

function addLabel(text, parent) {    	
	var label = document.createElement("label");
	label.innerHTML = text;
	parent.appendChild(label);
}

function addInputText(name, parent) {
	var newInput = document.createElement("input");         
	newInput.name = name;
	parent.appendChild(newInput); 
}     