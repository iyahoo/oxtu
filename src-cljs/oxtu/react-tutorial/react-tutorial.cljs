(ns oxtu.cljs.react.tutorial
  (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(defonce app-state (atom {:text "Hello world!"}))

(defn shopping-list [& {:keys [name]}]
  [:div.shopping-list
   [:h1 (str "Shopping List for" name)]
   [:ul
    [:li "Instagram"]
    [:li "WhatsApp"]
    [:li "Oculus"]]])

;; class Square extends React.Component {
;;   render() {
;;     return (
;;       <button className="square">
;;         {/* TODO */}
;;       </button>
;;     );
;;   }
;; }

;; class Board extends React.Component {
;;   renderSquare(i) {
;;     return <Square />;
;;   }

;;   render() {
;;     const status = 'Next player: X';

;;     return (
;;       <div>
;;         <div className="status">{status}</div>
;;         <div className="board-row">
;;           {this.renderSquare(0)}
;;           {this.renderSquare(1)}
;;           {this.renderSquare(2)}
;;         </div>
;;         <div className="board-row">
;;           {this.renderSquare(3)}
;;           {this.renderSquare(4)}
;;           {this.renderSquare(5)}
;;         </div>
;;         <div className="board-row">
;;           {this.renderSquare(6)}
;;           {this.renderSquare(7)}
;;           {this.renderSquare(8)}
;;         </div>
;;       </div>
;;     );
;;   }
;; }

;; class Game extends React.Component {
;;   render() {
;;     return (
;;       <div className="game">
;;         <div className="game-board">
;;           <Board />
;;         </div>
;;         <div className="game-info">
;;           <div>{/* status */}</div>
;;           <ol>{/* TODO */}</ol>
;;         </div>
;;       </div>
;;     );
;;   }
;; }

;; // ========================================

;; ReactDOM.render(
;;   <Game />,
;;   document.getElementById('root')
;; );

(defn text []
  )

(reagent/render-component
 [shopping-list]
 (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  )
