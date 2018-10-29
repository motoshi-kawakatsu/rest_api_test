/**
 *
 */

class MyAccount extends React.Component{
  hoge = "にゃろめ"

  constructor(props){
    super(props);
  }
  state = { memberId: '',
		  memberName: ''};

  	onNameChange = e => {
	    this.setState({ memberName: e });
	};
	onHogeChange = e => {
		    this.hoge = 'シェー！';
	};

  componentWillMount(){
	  return fetch('/MyAccount/GetAccountInfo').then(res => res.json()).then(data => {
			console.log('fetch結果取得');
			console.dir(data);
			this.setState({
				memberId: (data.memberId ? data.memberId : ''),
				memberName: (data.memberName ? data.memberName : '')
			});

			console.dir(this.state)
	  }).catch((error) => {
			console.error(error);
	  });
  }
  render(){
	  console.log("render");
	  return <div id="member_info">
	  			<p>会員ID : {this.state.memberId}</p>
	  			<p>会員名 : {this.state.memberName}</p>
	  			<p>{this.hoge}</p>
	  			<input type="button" onClick={() => this.onNameChange('石原　さとみ')} value="名前変更テスト" />
	  			<input type="button" onClick={() => this.onHogeChange()} value="いやみ" />
	  		  </div>;
  }
}


ReactDOM.render(
	<MyAccount />,
	document.getElementById('content')
);

var nameChange = function(name){
	MyAccount.state.memberName = name;
}
