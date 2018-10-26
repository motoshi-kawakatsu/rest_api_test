class hello extends React.Component {

	constructor(prop) {
		super(prop);
	}
	
	render() {
		return (
			<div>
				<form
					className="input-id"
					onSubmit={this.handleSubmit}>
					<input
						type="text"
						value={this.state.inpuValue}
						onChange={this.handleChange} />
					<input
						type="submit"
						value="send" />
				</form>
			</div>
		);
	}
	
	handleSubmit(e) {
		e.preventDefault();
		var number = this.state.inputValue;
		this.setState({
			inputValue: ''
		});
	}
	
	handleChange(e) {
		this.setState({
			inputValue: e.target.value
		});
	}

}

ReactDOM.render(
	<hello />,
	document.getElementById('content')
);
