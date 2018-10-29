class Hello extends React.Component {

	constructor(props){
		super(props);
	};
	state = { employeeId: '',
			  employeeName: '',
			  employeeAge: '' };

	render() {
		return (
			<div>
				<form onSubmit={this.handleSubmit}>
					<label htmlFor="id">社員ID：</label>
					<input type="text" name="employeeId" value={this.state.employeeId} />
					<button type="submit">検索</button>
				</form>
				<div>
					<label htmlFor="name">名前：</label>
					<input type="text" name="employeeName" value={this.state.employeeName} onChange={this.handleChange} />
				</div>
				<div>
					<label htmlFor="age">年齢：</label>
					<input type="text" name="employeeAge" value={this.state.employeeAge} onChange={this.handleChange} />
				</div>
			</div>
		);
	}
	
	handleChange = event => {
	
        switch (event.target.name) {
            case 'employeeId':
                this.setState({ employeeId: event.target.value });
                break;
            case 'employeeName':
                this.setState({ employeeName: event.target.value });
                break;
            case 'employeeAge':
                this.setState({ employeeAge: event.target.value });
                break;
        }

	}
	
	var handleSubmit = function() {
		fetch('/person/1').then(res => {
			if(res.OK) {
				console.log("OK");
			} else {
				console.log("error");
			}
		});
	}
	
}

ReactDOM.render(
	<Hello />,
	document.getElementById('content')
);
