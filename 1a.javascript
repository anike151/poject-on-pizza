function translate(text) {
    return text.replace(/([bcdfghjklmnpqrstvwxyz])/gi, '$1o$1');
}
console.log(translate("this is fun"));  
