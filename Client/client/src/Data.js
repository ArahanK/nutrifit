export let labels = [];
export let values = [];

export function getChartData(){
    var requestOptions = {
        method: 'GET',
        redirect: 'follow'
      }; 

      
    fetch("http://localhost:8080/Food", requestOptions)
      .then((response) => response.json())
      .then((data) => {
        let temp = data;
        for(const element of temp){
            labels.push(element);
            //just adding random value atm
            values.push(21);
        }
      })    
      .catch(function(){
        console.log("ERROR")
      });
}

export let data = {
  labels,
  datasets: [
    {
      data: values
    },
  ],
};
