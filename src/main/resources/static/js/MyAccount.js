/**
 *
 */

class MyAccount extends React.Component{
  hoge = "�ɂ���"

  constructor(props){
    super(props);
  }
  state = { memberId: '',
		  memberName: ''};

  	onNameChange = e => {
	    this.setState({ memberName: e });
	};
	onHogeChange = e => {
		    this.hoge = '�V�F�[�I';
	};

  componentWillMount(){
	  return fetch('/MyAccount/GetAccountInfo').then(res => res.json()).then(data => {
			console.log('fetch���ʎ擾');
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
	  			<p>���ID : {this.state.memberId}</p>
	  			<p>����� : {this.state.memberName}</p>
	  			<p>{this.hoge}</p>
	  			<input type="button" onClick={() => this.onNameChange('�Ό��@���Ƃ�')} value="���O�ύX�e�X�g" />
	  			<input type="button" onClick={() => this.onHogeChange()} value="�����" />
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
