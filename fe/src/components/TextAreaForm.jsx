import { useState } from 'react';
import './TextAreaForm.css';

export default function Home() {
  const [inputText, setInputText] = useState('');
  const [outputText, setOutputText] = useState('');

  const handleSubmit = async () => {
    try {
      const response = await fetch('/api/execute', {
        method: 'POST',
        headers: { 'Content-Type': 'text/plain' },
        body: inputText,
      });
      const data = await response.text();
      setOutputText(data);
    } catch (error) {
      setOutputText('Error: Unable to connect to the server.');
    }
  };

  return (
    <div className="app-container">
      <div className="content-container">
        <h1 className="heading">
          <span className="large">K</span>
          <span className="small">NUDON </span>
        </h1>
        <div className="input-container">
          <textarea
            className="input-textarea"
            placeholder="Enter text here"
            value={inputText}
            onChange={(e) => setInputText(e.target.value)}
          ></textarea>
        </div>
        <textarea
          className="output-textarea"
          placeholder="> Waiting for response..."
          value={outputText}
          readOnly
        ></textarea>
        <button className="submit-button" onClick={handleSubmit}>
          Submit
        </button>
        <div className="copyright">
          © <span className='small'> 2025</span>
          <span className="large"> R</span>
          <span className="small">AJARSHI</span>
        </div>
      </div>
    </div>
  );
}
