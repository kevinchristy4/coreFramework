const { browser, protractor, element } = require("protractor");
const axios = require('axios').default;

var createAssesments = function(){


    this.post = async (url, header, body) => {
      try {
        const loginResponse = await axios({
          method: "post",
          url: url,
          headers: header,
          data: body,
        });
      } catch (errors) {
        console.error(errors);
      }
    };


    this.get = async (url, header, body) => {
        try {
          const loginResponse = await axios({
            method: "get",
            url: url,
            headers: header,
            data: body,
          });
        } catch (errors) {
          console.error(errors);
        }
      };



}
module.exports = new createAssesments();