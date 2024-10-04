import { useEffect, useState } from "react";
import type { Schema } from "../amplify/data/resource";
import { generateClient } from "aws-amplify/data";

const client = generateClient<Schema>();

function App() {
  const [members, setMembers] = useState<Array<Schema["Member"]["type"]>>([]);

  useEffect(() => {
    client.models.Member.observeQuery().subscribe({
      next: (data) => setMembers([...data.items]),
    });
  }, []);

  function createMember() {
    var a = client.models.Member.create({ name: window.prompt("Name") ?? "Jon" });
    console.log(a);
  }

  return (
    <main>
      <h1>My todos</h1>
      <button onClick={createMember}>+ new</button>
      <ul>
        {members.map((member) => (
          <li key={member.id}>{member.name}</li>
        ))}
      </ul>
      <div>
        🥳 App successfully hosted. Try creating a new todo.
        <br />
        <a href="https://docs.amplify.aws/react/start/quickstart/#make-frontend-updates">
          Review next step of this tutorial.
        </a>
      </div>
    </main>
  );
}

export default App;
